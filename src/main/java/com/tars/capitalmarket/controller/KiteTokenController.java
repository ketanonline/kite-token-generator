package com.tars.capitalmarket.controller;

import com.tars.capitalmarket.service.KiteTokenService;
import com.zerodhatech.models.User;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class KiteTokenController {


    private KiteTokenService kiteTokenService;

    public KiteTokenController(KiteTokenService kiteTokenService) {
        this.kiteTokenService = kiteTokenService;
    }

    static final Logger logger = LoggerFactory.getLogger(KiteTokenController.class);
    @Get(uri = "/token")
    public User getToken() {
        logger.info("Generating Token");
        return kiteTokenService.generateToken();
    }

}
