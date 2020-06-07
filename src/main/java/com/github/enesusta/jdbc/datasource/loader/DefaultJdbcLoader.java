package com.github.enesusta.jdbc.datasource.loader;

import com.github.enesusta.jloader.DefaultPropertiesLoader;
import com.github.enesusta.jloader.PropertiesLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DefaultJdbcLoader implements JdbcLoader {

    @Override
    public Map<String, String> getProperties() {
        Map<String, String> propertiesMap = null;

        final PropertiesLoader loader = new DefaultPropertiesLoader();

        try {

            final Properties properties = loader.load();

            propertiesMap = new HashMap<>();
            propertiesMap.put("username", properties.getProperty("jdbc.username"));
            propertiesMap.put("password", properties.getProperty("jdbc.password"));
            propertiesMap.put("driver-class-name", properties.getProperty("jdbc.driver-class-name"));
            propertiesMap.put("host", properties.getProperty("jdbc.host"));
            propertiesMap.put("database", properties.getProperty("jdbc.database"));


        } catch (IOException e) {
            e.printStackTrace();
        }

        return propertiesMap;
    }
}
