package com.mobipixel.marketdata.entities.impl;

import com.mobipixel.marketdata.entities.RuleResult;

import java.util.Date;

/**
 * Created by Kevin on 5/29/16.
 */
public class TargetPriceRuleResultImpl implements RuleResult {
    private String description;
    private boolean result;

    private TargetPriceRuleImpl rule;
    private Double price;
    private Date date;

    public TargetPriceRuleResultImpl(TargetPriceRuleImpl rule, Date date) {
        this.rule = rule;
        this.date = date;
    }

    public boolean getResult() {
        return this.result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TargetPriceRuleImpl getRule() {
        return this.rule;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return this.price;
    }

}
