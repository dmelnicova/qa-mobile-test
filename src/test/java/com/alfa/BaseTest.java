package com.alfa;

import com.alfa.constans.IConstants;
import com.alfa.utils.AppiumDriverManager;
import org.openqa.selenium.HasCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.lang.invoke.MethodHandles;

public class BaseTest implements IConstants {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Parameters({"platformName", "deviceName"})
    @BeforeMethod()
    public void setUp(String platformName, String deviceName) {
        LOGGER.info("BaseTest: starting driver.");
        AppiumDriverManager.initDriver(platformName, deviceName);
        LOGGER.info("Driver capabilities:");
        LOGGER.info(((HasCapabilities) AppiumDriverManager.getDriver()).getCapabilities().asMap().toString());
        LOGGER.info("BaseTest: started driver.");
    }

    @AfterMethod()
    public void tearDown() {
        AppiumDriverManager.closeDriver();
        LOGGER.info("BaseTest: closed driver.");
    }

}
