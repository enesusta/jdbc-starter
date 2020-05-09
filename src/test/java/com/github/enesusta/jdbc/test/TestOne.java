package com.github.enesusta.jdbc.test;

import com.github.enesusta.jdbc.datasource.JdbcConfiguration;
import com.github.enesusta.jdbc.datasource.JdbcDataSource;
import com.github.enesusta.jdbc.datasource.PostgreJdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

public class TestOne {

    private JdbcDataSource jdbcDataSource;
    private DataSource dataSource;

    @BeforeEach
    public void init() {

        JdbcConfiguration configuration = new JdbcConfiguration.JdbcConfigurationBuilder()
                .username("docker")
                .password("123456")
                .driverClassName("org.postgresql.Driver")
                .jdbcUrl("jdbc:postgresql://localhost:5432/docker?characterEncoding=utf8")
                .build();

        jdbcDataSource = new PostgreJdbcDataSource();
        dataSource = jdbcDataSource.getDataSource(configuration);

    }

    @Test
    public void connection_test() {

        

    }


}
