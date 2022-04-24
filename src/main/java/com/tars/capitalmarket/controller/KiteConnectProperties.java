package com.tars.capitalmarket.controller;

import io.micronaut.context.annotation.ConfigurationProperties;
import jakarta.inject.Singleton;

@ConfigurationProperties("app.kite")
@Singleton
public class KiteConnectProperties {

    private String apikey;
    private String apisecret;
    private String userid;
    private String userpassword;
    private String twofactorpin;
    private String chromedriverpath;

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getApisecret() {
        return apisecret;
    }

    public void setApisecret(String apisecret) {
        this.apisecret = apisecret;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getTwofactorpin() {
        return twofactorpin;
    }

    public void setTwofactorpin(String twofactorpin) {
        this.twofactorpin = twofactorpin;
    }

    public String getChromedriverpath() {
        return chromedriverpath;
    }

    public void setChromedriverpath(String chromedriverpath) {
        this.chromedriverpath = chromedriverpath;
    }
}
