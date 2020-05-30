package com.github.enesusta.jdbc.datasource;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public final class ApacheCommonsDbcpJdbcDataSource implements JdbcDataSource {

    private final JdbcConfiguration jdbcConfiguration;

    public ApacheCommonsDbcpJdbcDataSource(final JdbcConfiguration jdbcConfiguration) {
        this.jdbcConfiguration = jdbcConfiguration;
    }

    @Override
    public final DataSource getDataSource() {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(jdbcConfiguration.getJdbcUrl());
        dataSource.setUsername(jdbcConfiguration.getUsername());
        dataSource.setPassword(jdbcConfiguration.getPassword());
        dataSource.setDriverClassName(jdbcConfiguration.getDriverClassName());

        return dataSource;
    }
}
