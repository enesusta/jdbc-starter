package com.github.enesusta.jdbc.datasource;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public final class SQLiteJdbcDataSource implements JdbcDataSource {

    private final JdbcConfiguration jdbcConfiguration;

    public SQLiteJdbcDataSource(final JdbcConfiguration jdbcConfiguration) {
        this.jdbcConfiguration = jdbcConfiguration;
    }

    @Override
    public final DataSource getDataSource() {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(jdbcConfiguration.getJdbcUrl());
        dataSource.setDriverClassName(jdbcConfiguration.getDriverClassName());

        return dataSource;
    }
}
