package com.mobipixel.marketdata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

import javax.swing.*;

/**
 * Created by Kevin on 5/20/16.
 */
@ComponentScan
@Controller
@EnableAutoConfiguration(exclude = { JacksonAutoConfiguration.class })
public class Application {

    static Logger log = LoggerFactory.getLogger(Application.class);
    public static ApplicationContext context;
    public static void main(String[] args) throws Exception{

        log.info("MobiPixel - MarketData System has activated.");

        Application.context = SpringApplication.run(Application.class, args);
    }

}
