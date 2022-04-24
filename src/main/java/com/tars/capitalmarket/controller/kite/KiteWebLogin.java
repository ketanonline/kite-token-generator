package com.tars.capitalmarket.controller.kite;

import com.tars.capitalmarket.controller.KiteConnectProperties;
import jakarta.inject.Singleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class KiteWebLogin {

    KiteConnectProperties kiteConnectProperties;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public KiteWebLogin(KiteConnectProperties kiteConnectProperties) {
        this.kiteConnectProperties = kiteConnectProperties;
    }

    public String autoWebLogin(String redirectUrl)
            throws IOException, InterruptedException {
        String requestToken = null;
        // We need the FireFox WebDriver for Selenium
        logger.info("KiteSession.autoWebLogin() || cromedriver={}  ", kiteConnectProperties.getChromedriverpath());

        System.setProperty("webdriver.chrome.driver", kiteConnectProperties.getChromedriverpath());

        // Get the first log in URL.

        // Initialize the drivers
        ChromeOptions options = new ChromeOptions().setHeadless(true);
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        WebDriver webDriver = new ChromeDriver(options);
        WebDriverWait waitDriver = new WebDriverWait(webDriver, 10);

        // Get the Kite Login page. Use the User ID & Password to submit the form.
        logger.info("KiteSession.autoWebLogin() || redirectUrl={}  ", redirectUrl);
        webDriver.get(redirectUrl);
        String curUrl = webDriver.getCurrentUrl();
        WebElement loginField = webDriver.findElement(By.id("userid"));
        WebElement pwdField = webDriver.findElement(By.id("password"));
        WebElement submitButton = webDriver
                .findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/div/div/div[2]/form/div[4]/button"));
        loginField.sendKeys(kiteConnectProperties.getUserid());
        pwdField.sendKeys(kiteConnectProperties.getUserpassword());
        submitButton.click();
        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));

        // We are now in the 2FA page. Enter the PIN and submit the form.
        curUrl = webDriver.getCurrentUrl();

        WebElement twoFaField = webDriver.findElement(By.cssSelector("#pin"));
        WebElement twoFaButton = webDriver.findElement(By.cssSelector(".button-orange"));
        // NOTE: Select by XPath was working and should work. Not sure why it stopped.
        // Have to review and figure it out.
        twoFaField.sendKeys(kiteConnectProperties.getTwofactorpin());
        twoFaButton.submit();

        // waitDriver.until(ExpectedConditions.urlContains(redirectUrl));
        Thread.sleep(20000);
        curUrl = webDriver.getCurrentUrl();
        logger.info("KiteSession.autoWebLogin() || curUrl={}", curUrl);
        URL url = new URL(curUrl);
        Map<String, String> query_pairs = new HashMap<>();
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8.toString()),
                    URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8.toString()));
        }
        requestToken = query_pairs.get("request_token");
        logger.info("KiteSession.autoWebLogin() || requestToken={}", requestToken);
        webDriver.close();
        return requestToken;
    }
}
