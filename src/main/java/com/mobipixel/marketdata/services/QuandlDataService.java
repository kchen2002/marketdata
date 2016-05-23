package com.mobipixel.marketdata.services;

import com.google.gson.JsonElement;

/**
 * Created by Kevin on 5/21/16.
 */
public interface QuandlDataService {

    public JsonElement requestTicker(String ticker);
}
