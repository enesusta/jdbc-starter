package com.github.enesusta.jdbc.datasource.loader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DefaultJdbcLoaderTest {

    private JdbcLoader loader;
    private Map<String, String> map;

    @BeforeEach
    void setUp() {
        loader = new DefaultJdbcLoader();
        map = loader.getProperties();
    }

    @Test
    void should_returnTrue_usernameProp() {
        String username = map.get("username");
        assertEquals("docker", username);
    }

    @Test
    void should_returnTrue_PasswordProp() {
        String pwd = map.get("password");
        assertEquals("mykenai1363", pwd);
    }

    @Test
    void should_returnTrue_driverClassNameProp() {
        String driverClassName = map.get("driver-class-name");
        assertEquals("org.postgresql.Driver", driverClassName);
    }

    @Test
    void should_returnTrue_hostProp() {
        String host = map.get("host");
        assertEquals("localhost", host);
    }

    @Test
    void should_returnTrue_databaseProp() {
        String database = map.get("database");
        assertEquals("docker", database);
    }

}
