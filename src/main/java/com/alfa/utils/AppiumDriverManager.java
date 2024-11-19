package com.alfa.utils;

import com.alfa.constans.IConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverManager implements IConstants {

    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void initDriver(String platformName, String deviceName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:platformName", platformName);
        capabilities.setCapability("appium:deviceName", deviceName);
        capabilities.setCapability("app", APP_PATH);
        try {
            switch (platformName) {
                case "android":
                    capabilities.setCapability("appium:automationName", AutomationName.ANDROID_UIAUTOMATOR2);
                    driver.set(new AndroidDriver(new URL(SELENIUM_URL), capabilities));
                    LOGGER.info("Created android driver.");
                    break;
                case "ios":
                    capabilities.setCapability("appium:automationName", AutomationName.IOS_XCUI_TEST);
                    driver.set(new IOSDriver(new URL(SELENIUM_URL), capabilities));
                    LOGGER.info("Created ios driver.");
                default:
                    throw new RuntimeException(String.format("Cannot create driver for platform: '%s'!", platformName));
            }
        } catch (MalformedURLException e) {
            LOGGER.error("Cannot access to selenium url: '{}'! {}", SELENIUM_URL, e.getMessage());
        }
    }

    public static void closeDriver() {
        driver.get().quit();
        driver.remove();
    }
}
