package com.github.enesusta.jdbc.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public final class HikariJdbcDataSource implements JdbcDataSource {

    @Override
    public final DataSource getDataSource(final JdbcConfiguration jdbcConfiguration) {

        final HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcConfiguration.getJdbcUrl());
        config.setUsername(jdbcConfiguration.getUsername());
        config.setPassword(jdbcConfiguration.getPassword());
        config.setDriverClassName(jdbcConfiguration.getDriverClassName());
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "true");

        final HikariDataSource ds = new HikariDataSource(config);

        return ds;

    }
}
