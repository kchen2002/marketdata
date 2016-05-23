package com.mobipixel.marketdata.controllers;

import com.google.gson.JsonElement;
import com.mobipixel.marketdata.services.QuandlDataService;
import com.mobipixel.marketdata.services.impl.QuandlDataServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value="/ticker/{ticker}",produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    JsonElement getTicker(@PathVariable String ticker) {
        return quandlDataService.requestTicker(ticker);
    }
}
