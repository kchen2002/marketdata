package com.mobipixel.marketdata.services;

import com.mobipixel.marketdata.entities.RuleResult;

import java.util.List;

/**
 * Created by Kevin on 5/29/16.
 */
public interface PortfolioService {

    List<RuleResult> runPortfolioRules(String id);
}
