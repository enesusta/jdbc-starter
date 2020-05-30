package com.github.enesusta.jdbc.datasource.enums;

public enum DatabaseType {

    POSTGRE("org.postgresql.Driver", 5432),
    MYSQL("com.mysql.cj.jdbc.Driver", 3306),
    SQLITE("org.sqlite.JDBC", 0);

    private final String type;
    private final int port;

    DatabaseType(final String type,
                 final int port) {
        this.type = type;
        this.port = port;
    }

    public String getDriverClassName() {
        return type;
    }

    public int getPort() {
        return port;
    }
}
