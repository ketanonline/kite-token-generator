package com.tars.capitalmarket.controller;

import com.tars.capitalmarket.controller.kite.KiteWebLogin;
import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.User;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

@Controller
public class KiteTokenController {

    private ChromeDriver chromeDriver;

    public KiteTokenController(ChromeDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    static final Logger logger = LoggerFactory.getLogger(KiteTokenController.class);
    @Get(uri = "/token")
    public Map<String, String> getToken() {
        logger.info("Generating Token");

        KiteConnectProperties kiteConnectProperties = new KiteConnectProperties();
        kiteConnectProperties.setApikey("zb1e49df58y743sp");
        kiteConnectProperties.setApisecret("59wj4aa57kya9mqijsa9x6vs76huh4ud");
        kiteConnectProperties.setUserid("VHL351");
        kiteConnectProperties.setUserpassword("Krishna$1Billion");
        kiteConnectProperties.setTwofactorpin("653283");

        kiteConnectProperties.setChromedriverpath(chromeDriver.chromePath());
        KiteWebLogin kiteWebLogin = new KiteWebLogin(kiteConnectProperties);
        KiteConnect kiteConnect = new KiteConnect(kiteConnectProperties.getApikey());
        String url = "https://kite.trade/connect/login?api_key=zb1e49df58y743sp&v=3";
        try {
            String requestToken = kiteWebLogin.autoWebLogin(url);
            User user = kiteConnect.generateSession(requestToken, kiteConnectProperties.getApisecret());
            System.out.println("user.accessToken = " + user.accessToken + " user.publicToken:" + user.publicToken);
            return Map.of("user.accessToken", user.accessToken, "user.publicToken", user.publicToken);

        } catch (IOException | InterruptedException | KiteException e) {
            throw new RuntimeException(e);
        }

    }

}
