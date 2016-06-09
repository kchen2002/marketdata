package com.mobipixel.marketdata.jobs;

import com.mobipixel.marketdata.Application;
import com.mobipixel.marketdata.services.PortfolioService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
/**
 * Created by Kevin on 6/6/16.
 */
public class PortfolioJob implements org.quartz.Job{

    public void execute(JobExecutionContext context) throws JobExecutionException {
        if (Application.context != null) {
            PortfolioService portfolioService = (PortfolioService) Application.context.getBean("PortfolioServiceImpl");
            portfolioService.runPortfolioRules("1234");
        }
    }


}
