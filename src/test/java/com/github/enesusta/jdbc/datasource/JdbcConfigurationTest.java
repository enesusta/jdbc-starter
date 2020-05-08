package com.github.enesusta.jdbc.datasource;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JdbcConfigurationTest {

    @Test
    void should_returnSameUrl_when_newConfigurationInstanceCreatedWithBuilder() {

        JdbcConfiguration configuration = new JdbcConfiguration.JdbcConfigurationBuilder()
                .jdbcUrl("test1")
                .username("username1")
                .password("pw1")
                .driverClassName("driver1")
                .build();

        assertEquals("test1", configuration.getJdbcUrl());

    }


}