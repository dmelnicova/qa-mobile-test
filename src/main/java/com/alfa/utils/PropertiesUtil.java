package com.alfa.utils;

import com.alfa.constans.IConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.invoke.MethodHandles;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil implements IConstants {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static String getConfigValue(String value) {
        return getPropertyInFile(value, CONFIG_FILE_PATH);
    }

    public static String getTestDataValue(String value) {
        return getPropertyInFile(value, TESTDATA_FILE_PATH);
    }

    private static String getPropertyInFile(String value, String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties.getProperty(value);
        } catch (IOException e) {
            LOGGER.error("Unable to read the file! {}", e.getMessage());
            return "";
        }
    }
}
