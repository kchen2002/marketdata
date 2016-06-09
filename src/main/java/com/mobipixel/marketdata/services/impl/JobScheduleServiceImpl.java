package com.mobipixel.marketdata.services.impl;

import com.mobipixel.marketdata.jobs.PortfolioJob;
import com.mobipixel.marketdata.services.JobScheduleService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by Kevin on 5/28/16.
 */
@Service("JobScheduleServiceImpl")
public class JobScheduleServiceImpl implements JobScheduleService{

    Logger log = LoggerFactory.getLogger(JobScheduleService.class);

    JobScheduleServiceImpl() {
        JobDetail portfolioJob = newJob(PortfolioJob.class)
                .withIdentity("PortfolioJob", "Portfolio-group")
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("PortfolioTrigger", "Portfolio-group")
                .withSchedule(cronSchedule("0 34 16 ? * MON-FRI")).build();

        SchedulerFactory sf = new StdSchedulerFactory();

        try {
            Scheduler sched = sf.getScheduler();
            sched.scheduleJob(portfolioJob, trigger);
            sched.start();
        }
        catch(SchedulerException e) {
            log.error("Error running job schedule");
        }
    }
}
