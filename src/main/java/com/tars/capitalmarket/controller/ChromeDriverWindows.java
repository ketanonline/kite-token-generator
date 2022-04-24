package com.tars.capitalmarket.controller;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Value;
import io.micronaut.core.io.ResourceResolver;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Requires(os={Requires.Family.WINDOWS})
@Singleton
public class ChromeDriverWindows implements ChromeDriver {
    @Value("${chromedriverpath}")
    public String CLASSPATH_CHROMEDRIVER_WINDOWS_CHROMEDRIVER;
    static final Logger logger = LoggerFactory.getLogger(ChromeDriverWindows.class);

    private ResourceResolver resourceResolver;

    public ChromeDriverWindows(ResourceResolver resourceResolver) {
        this.resourceResolver = resourceResolver;
    }

    @Override
    public String chromePath() {
        logger.info("property loaded: " + CLASSPATH_CHROMEDRIVER_WINDOWS_CHROMEDRIVER);
        return CLASSPATH_CHROMEDRIVER_WINDOWS_CHROMEDRIVER;
    }
}
