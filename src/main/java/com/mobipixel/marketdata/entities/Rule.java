package com.mobipixel.marketdata.entities;

import java.util.Date;

/**
 * Created by Kevin on 5/29/16.
 */
public interface Rule {

    RuleResult runRule(Double value, Date date);
}
