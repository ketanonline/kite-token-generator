package com.tars.capitalmarket.controller;

import io.micronaut.context.annotation.Requires;
import io.micronaut.core.io.ResourceResolver;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Requires(os={Requires.Family.MAC_OS})
@Singleton
public class ChromeDriverMacOs implements ChromeDriver {

//    @Value("${chromedriver}")
    public String CLASSPATH_CHROMEDRIVER_OSX_CHROMEDRIVER;
    static final Logger logger = LoggerFactory.getLogger(ChromeDriverMacOs.class);
    private ResourceResolver resourceResolver;

    public ChromeDriverMacOs(ResourceResolver resourceResolver) {
        this.resourceResolver = resourceResolver;
    }

    @Override
    public String chromePath() {
        logger.info(CLASSPATH_CHROMEDRIVER_OSX_CHROMEDRIVER);
        if(CLASSPATH_CHROMEDRIVER_OSX_CHROMEDRIVER == null) {
            CLASSPATH_CHROMEDRIVER_OSX_CHROMEDRIVER = "/Users/ketandhamasana/Downloads/chromedriver";
        }
        logger.info("chromedriver: " + CLASSPATH_CHROMEDRIVER_OSX_CHROMEDRIVER);
        return CLASSPATH_CHROMEDRIVER_OSX_CHROMEDRIVER;
    }
}
