package com.mobipixel.marketdata.services.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mobipixel.marketdata.services.QuandlDataService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Kevin on 5/21/16.
 */

@Service("QuandlDataServiceImpl")
public class QuandlDataServiceImpl implements QuandlDataService {
    private Logger log = LoggerFactory.getLogger(QuandlDataServiceImpl.class);

    public JsonElement request(String url) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = null;
        JsonElement root = null;
        try {
            response = client.execute(request);
            String json_string = EntityUtils.toString(response.getEntity());
            root = new JsonParser().parse(json_string);
        }
        catch (Exception e) {
            log.error("Error connecting to data service",e);
        }

        return root.getAsJsonObject().get("dataset");
    }

    public JsonElement requestTicker(String ticker) {
        //TODO: API URL could come from configuration
        String url = "https://www.quandl.com/api/v3/datasets/WIKI/" + ticker + ".json?start_date=05-01-2016";
        return this.request(url);
    }
}
