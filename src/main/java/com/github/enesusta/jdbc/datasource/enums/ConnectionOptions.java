package com.github.enesusta.jdbc.datasource.enums;


import java.util.HashMap;
import java.util.Map;

public enum ConnectionOptions {

    CURRENT_SCHEMA("currentSchema"),
    CHARACTER_ENCODING("characterEncoding");

    private String prop;
    private Map<String, String> propMap = new HashMap<>(5);

    ConnectionOptions(String prop) {
        this.prop = prop;
    }

    public String getProp() {
        return prop;
    }
}
