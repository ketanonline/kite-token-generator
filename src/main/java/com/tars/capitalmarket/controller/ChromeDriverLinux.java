package com.tars.capitalmarket.controller;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Value;
import io.micronaut.core.io.ResourceResolver;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Requires(os={Requires.Family.LINUX})
@Singleton
public class ChromeDriverLinux implements ChromeDriver {

    @Value("${chromedriver}")
    public String CLASSPATH_CHROMEDRIVER_LINUX_64_CHROMEDRIVER;
    static final Logger logger = LoggerFactory.getLogger(ChromeDriverLinux.class);
    private ResourceResolver resourceResolver;

    public ChromeDriverLinux(ResourceResolver resourceResolver) {
        this.resourceResolver = resourceResolver;
    }

    @Override
    public String chromePath() {
        logger.info("chromedriver: " + CLASSPATH_CHROMEDRIVER_LINUX_64_CHROMEDRIVER);
        return CLASSPATH_CHROMEDRIVER_LINUX_64_CHROMEDRIVER;
    }
}
