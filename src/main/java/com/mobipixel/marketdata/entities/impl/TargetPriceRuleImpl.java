package com.mobipixel.marketdata.entities.impl;

import com.mobipixel.marketdata.entities.Rule;

import java.text.NumberFormat;
import java.util.Date;

/**
 * Created by Kevin on 5/29/16.
 */

public class TargetPriceRuleImpl implements Rule {
    private String symbol;
    private Double targetPrice;
    private Double threshold;
    private String type = "TARGET_PRICE_RULE";

    public TargetPriceRuleImpl(String symbol, Double price) {
        this(symbol, price, 0.05);
    }

    public TargetPriceRuleImpl(String symbol, Double price, Double threshold) {
        this.symbol = symbol;
        this.targetPrice = price;
        this.threshold = threshold;
    }

    public String getSymbol() {
        return symbol;
    }

    public TargetPriceRuleResultImpl runRule(Double price, Date date) {
        TargetPriceRuleResultImpl result = new TargetPriceRuleResultImpl(this, date);
        double thresholdPrice = targetPrice * (1 - this.threshold);

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        if (thresholdPrice < price) {
            result.setDescription("Price is within target price threshold " + formatter.format(thresholdPrice));
            result.setResult(true);
        }
        else {
            result.setDescription("Price is below threshold price " + formatter.format(thresholdPrice));
            result.setResult(false);
        }

        result.setPrice(price);
        return result;
    }
}
