package com.github.enesusta.jdbc.datasource;

public class JdbcConfiguration {

    private String jdbcUrl;
    private String username;
    private String password;
    private String driverClassName;

    public JdbcConfiguration() {

    }

    public JdbcConfiguration(final JdbcConfigurationBuilder builder) {
        this.jdbcUrl = builder.jdbcUrl;
        this.username = builder.username;
        this.password = builder.password;
        this.driverClassName = builder.driverClassName;
    }

    public String getJdbcUrl() {
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

        public JdbcConfiguration build() {
            return new JdbcConfiguration(this);
        }
    }
}
