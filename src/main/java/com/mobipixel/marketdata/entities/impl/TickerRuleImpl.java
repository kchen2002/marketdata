package com.mobipixel.marketdata.entities.impl;

import com.mobipixel.marketdata.entities.Rule;

import java.util.Date;

/**
 * Created by Kevin on 5/29/16.
 */

public class TickerRuleImpl implements Rule {
    private String symbol;
    private Double targetPrice;
    private Double threshold;



    public static TickerRuleImpl makeTickerRule(String symbol, Double price) {
        return new TickerRuleImpl(symbol, price);
    }

    public TickerRuleImpl(String symbol, Double price) {
        this(symbol, price, 0.05);
    }

    public TickerRuleImpl(String symbol, Double price, Double threshold) {
        this.symbol = symbol;
        this.targetPrice = price;
        this.threshold = threshold;
    }

    public String getSymbol() {
        return symbol;
    }

    public TickerRuleResult runRule(Double price, Date date) {
        TickerRuleResult result = new TickerRuleResult(this, date);
        double thresholdPrice = targetPrice * (1 - this.threshold);
        if (thresholdPrice < price) {
            result.setDescription("Price is within target price threshold $" + thresholdPrice);
            result.setResult(true);
        }
        else {
            result.setDescription("Price is below threshold price $" + thresholdPrice);
            result.setResult(false);
        }

        result.setPrice(price);
        return result;
    }
}
