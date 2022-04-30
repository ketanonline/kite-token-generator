package com.tars.capitalmarket.service;

import com.tars.capitalmarket.controller.ChromeDriver;
import com.tars.capitalmarket.controller.KiteConnectProperties;
import com.tars.capitalmarket.controller.kite.KiteWebLogin;
import com.tars.capitalmarket.repository.KiteTokenRepository;
import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.User;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Singleton
public class KiteTokenService {
    private ChromeDriver chromeDriver;

    static final Logger logger = LoggerFactory.getLogger(KiteTokenService.class);
    private KiteTokenRepository kiteTokenRepository;

    public KiteTokenService(ChromeDriver chromeDriver, KiteTokenRepository kiteTokenRepository) {
        this.chromeDriver = chromeDriver;
        this.kiteTokenRepository = kiteTokenRepository;
    }

    @PostConstruct
    public void saveToken() {
        logger.info("Generating token");
        User token = generateToken();
        kiteTokenRepository.save(token);
    }

    public User generateToken() {
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
            logger.info("user.accessToken = " + user.accessToken + " user.publicToken:" + user.publicToken);
            return user;

        } catch (IOException | InterruptedException | KiteException e) {
            throw new RuntimeException(e);
        }
    }
}
