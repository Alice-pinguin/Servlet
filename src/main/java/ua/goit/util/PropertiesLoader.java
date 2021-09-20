package ua.goit.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    private static final String propertiesName = "application.properties";
    private static final Properties properties;

    static {
        properties = new Properties();
        try (InputStream inputStream = getInputStream(propertiesName)) {
            properties.load(inputStream);
        } catch (IOException ex) {
            throw new RuntimeException("Invalid information");
        }
    }

    public static String getProperty (String name) {
        return properties.getProperty(name);
    }

    public static InputStream getInputStream(String propertiesFileName) {
        return PropertiesLoader.class.getClassLoader().getResourceAsStream(propertiesFileName);
    }
}
