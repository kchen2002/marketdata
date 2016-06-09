package com.mobipixel.marketdata.controllers;

import com.google.gson.JsonElement;
import com.mobipixel.marketdata.entities.RuleResult;
import com.mobipixel.marketdata.services.JobScheduleService;
import com.mobipixel.marketdata.services.PortfolioService;
import com.mobipixel.marketdata.services.QuandlDataService;
import com.mobipixel.marketdata.services.impl.JobScheduleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Kevin on 5/22/16.
 */
@ComponentScan
@Controller
@RequestMapping("marketdata")
public class MarketDataController {

    Logger log = LoggerFactory.getLogger(MarketDataController.class);

    @Autowired
    QuandlDataService quandlDataService;

    @Autowired
    PortfolioService portfolioService;

    @Autowired
    JobScheduleService jobScheduleService;

    @RequestMapping(value="/ticker/{ticker}",produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    JsonElement getTicker(@PathVariable String ticker) {
        return quandlDataService.requestTicker(ticker);
    }

    @RequestMapping(value="/portfolio/{id}/run",produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody List<RuleResult> runPortfolioRules(@PathVariable String id) {
        List<RuleResult> results = portfolioService.runPortfolioRules(id);
        return results;
    }
}
