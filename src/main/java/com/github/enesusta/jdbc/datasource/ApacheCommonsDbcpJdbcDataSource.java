package com.github.enesusta.jdbc.datasource;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public final class ApacheCommonsDbcpJdbcDataSource implements JdbcDataSource {

    @Override
    public final DataSource getDataSource(final JdbcConfiguration jdbcConfiguration) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(jdbcConfiguration.getJdbcUrl());
        dataSource.setUsername(jdbcConfiguration.getUsername());
        dataSource.setPassword(jdbcConfiguration.getPassword());
        dataSource.setDriverClassName(jdbcConfiguration.getDriverClassName());

        return dataSource;
    }
}
