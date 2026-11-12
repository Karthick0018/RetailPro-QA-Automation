package com.retailpro.utils;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {
    private static final Properties PROPS = new Properties();

    static {
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in != null) {
                PROPS.load(in);
            }
        } catch (Exception e) {
            throw new RuntimeException("Cannot load config.properties", e);
        }
    }

    private ConfigReader() {}

    /** Priority: -D system property, then config.properties. Unresolved ${...} values are ignored. */
    public static String get(String key) {
        String value = System.getProperty(key);
        if (value == null || value.isBlank() || value.startsWith("${")) {
            value = PROPS.getProperty(key);
        }
        return value;
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
