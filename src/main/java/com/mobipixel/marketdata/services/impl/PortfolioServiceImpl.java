package com.mobipixel.marketdata.services.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.mobipixel.marketdata.entities.Rule;
import com.mobipixel.marketdata.entities.RuleResult;
import com.mobipixel.marketdata.entities.impl.TargetPriceRuleImpl;
import com.mobipixel.marketdata.services.PortfolioService;
import com.mobipixel.marketdata.services.QuandlDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Kevin on 5/29/16.
 */
@Service("PortfolioServiceImpl")
public class PortfolioServiceImpl implements PortfolioService{

    Logger log = LoggerFactory.getLogger(PortfolioService.class);

    @Autowired
    QuandlDataService quandl;

    public RuleResult runRule(Rule r) {
        RuleResult rs = null;
        if (r instanceof TargetPriceRuleImpl) {
            TargetPriceRuleImpl stockRule = (TargetPriceRuleImpl) r;
            JsonElement json = quandl.requestTicker(stockRule.getSymbol());

            JsonArray dataset = json.getAsJsonObject().get("data").getAsJsonArray().get(0).getAsJsonArray();
            double price = dataset.get(4).getAsDouble();


            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date =  formatter.parse(dataset.get(0).getAsString());
            }
            catch (ParseException e) {
                log.error("Error parsing date from ticker data");
            }

            rs = ((TargetPriceRuleImpl) r).runRule(price, date);
        }

        return rs;
    }

    public List<RuleResult> runPortfolioRules(String id) {

        List<RuleResult> results = new ArrayList<>();
        TargetPriceRuleImpl stockRule = new TargetPriceRuleImpl("INO", 15.00);


        results.add(this.runRule(stockRule));
        results.add(this.runRule(new TargetPriceRuleImpl("AAPL", 98.00)));
        results.add(this.runRule(new TargetPriceRuleImpl("GILD", 120.00)));
        results.add(this.runRule(new TargetPriceRuleImpl("IBM", 160.00)));

        log.info("Portfolio run...");
        return results;
    }
}
