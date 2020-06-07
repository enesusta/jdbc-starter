package com.github.enesusta.jdbc.datasource;

import com.github.enesusta.jdbc.datasource.enums.DatabaseType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JdbcConfiguration {

    private String jdbcUrl;
    private String username;
    private String password;
    private String driverClassName;
    private String host;
    private String selectedDatabase;
    private int port;
    private Set<String> options;
    private DatabaseType databaseType;
    private String sqliteDatabaseLocation;
    private Map<DatabaseType, String> databaseTypeStringMap = new HashMap<>(5);

    public JdbcConfiguration(final JdbcConfigurationBuilder builder) {
        this.jdbcUrl = builder.jdbcUrl;
        this.username = builder.username;
        this.password = builder.password;
        this.host = builder.host;
        this.databaseType = builder.databaseType;
        this.port = builder.port;
        this.selectedDatabase = builder.selectedDatabase;
        this.options = builder.options;
        this.driverClassName = builder.driverClassName != null ? builder.driverClassName : builder.databaseType.getDriverClassName();
        this.sqliteDatabaseLocation = builder.sqliteDatabaseLocation;
        init();
    }

    private void init() {
        databaseTypeStringMap.put(DatabaseType.POSTGRE, "postgresql");
        databaseTypeStringMap.put(DatabaseType.MYSQL, "mysql");
        databaseTypeStringMap.put(DatabaseType.SQLITE, "sqlite");
    }

    public String getJdbcUrl() {

        if (jdbcUrl == null) {
            port = port != 0 ? port : databaseType.getPort();
            final String optionsString = String.join("", options);
            jdbcUrl =
                String.format("jdbc:%s://%s:%d/%s?%s",
                    databaseTypeStringMap.get(databaseType), host, port, selectedDatabase, optionsString);
        } else if (sqliteDatabaseLocation != null) {
            jdbcUrl =
                String.format("jdbc:%s:%s", databaseTypeStringMap.get(databaseType), sqliteDatabaseLocation);
        }

        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public static class JdbcConfigurationBuilder {

        private String jdbcUrl;
        private String username;
        private String password;
        private String driverClassName;
        private String host = "localhost";
        private String selectedDatabase;
        private int port;
        private String sqliteDatabaseLocation;
        private Set<String> options = new HashSet<>(5);
        private DatabaseType databaseType;

        public JdbcConfigurationBuilder jdbcUrl(final String jdbcUrl) {
            this.jdbcUrl = jdbcUrl;
            return this;
        }

        public JdbcConfigurationBuilder username(final String username) {
            this.username = username;
            return this;
        }

        public JdbcConfigurationBuilder password(final String password) {
            this.password = password;
            return this;
        }

        public JdbcConfigurationBuilder driverClassName(final String driverClassName) {
            this.driverClassName = driverClassName;
            return this;
        }

        public JdbcConfigurationBuilder type(final DatabaseType databaseType) {
            this.databaseType = databaseType;
            return this;
        }

        public JdbcConfigurationBuilder host(final String host) {
            this.host = host;
            return this;
        }

        public JdbcConfigurationBuilder selectedDatabase(final String selectedDatabase) {
            this.selectedDatabase = selectedDatabase;
            return this;
        }

        public JdbcConfigurationBuilder port(final int port) {
            this.port = port;
            return this;
        }

        public JdbcConfigurationBuilder sqliteDatabaseLocation(final String sqliteDatabaseLocation) {
            this.sqliteDatabaseLocation = sqliteDatabaseLocation;
            return this;
        }

        public JdbcConfigurationBuilder options(final List<JdbcOption> list) {
            for (final JdbcOption option : list) {
                final String temp = String.format("%s=%s&", option.getConnectionOptions().getProp(), option.getValueOfProp());
                options.add(temp);
            }
            return this;
        }

        public JdbcConfiguration build() {
            return new JdbcConfiguration(this);
        }
    }
}
