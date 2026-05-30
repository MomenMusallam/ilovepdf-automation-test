package com.ilovepdf.configuration;

import com.ilovepdf.constants.FrameworkConstants;
import com.ilovepdf.exceptions.FrameworkException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class ConfigReader {

    private static Properties properties;

    private ConfigReader() {}

    static {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(FrameworkConstants.getConfigPath())) {
            properties.load(fis);
        } catch (IOException e) {
            throw new FrameworkException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new FrameworkException("Property not found: " + key);
        }
        return value;
    }

    public static String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
