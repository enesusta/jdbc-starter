package com.github.enesusta.jdbc.datasource;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public final class SQLiteJdbcDataSource implements JdbcDataSource {

    @Override
    public final DataSource getDataSource(final JdbcConfiguration jdbcConfiguration) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(jdbcConfiguration.getJdbcUrl());
        dataSource.setDriverClassName("org.sqlite.JDBC");

        return dataSource;
    }
}
