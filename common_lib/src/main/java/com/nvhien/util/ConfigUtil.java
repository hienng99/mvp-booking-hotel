package com.nvhien.util;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public final class ConfigUtil {
    public static String getConfig(final String key) {
        Properties prop = new Properties();
        String fileName = "config/app.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
            return prop.getProperty(key);
        } catch (FileNotFoundException fileNotFoundException) {
            log.error("Config file not found: {}", fileNotFoundException.getMessage());
            return null;
        } catch (IOException ioException) {
            log.error("Error while reading file: {}", ioException.getMessage());
            return null;
        }
    }

    public static int getConfigAsInt(final String key) {
        String confStr = getConfig(key);
        try {
            return Integer.parseInt(confStr);
        } catch (NumberFormatException numberFormatException) {
            log.error("Error while parsing config: {}", numberFormatException.toString());
            return 0;
        }
    }

    public static int getConfigAsInt(final String key, final int defaultValue) {
        String confStr = getConfig(key);
        try {
            return Integer.parseInt(confStr);
        } catch (NumberFormatException numberFormatException) {
            log.error("Error while parsing config: {}", numberFormatException.toString());
            return defaultValue;
        }
    }
}
