package com.mobipixel.marketdata.entities;

/**
 * Created by Kevin on 5/29/16.
 */
public interface RuleResult {
    boolean getResult();

    String getDescription();
    void setDescription(String description);
}
