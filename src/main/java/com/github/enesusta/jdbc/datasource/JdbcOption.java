package com.github.enesusta.jdbc.datasource;

import com.github.enesusta.jdbc.datasource.enums.ConnectionOptions;

public final class JdbcOption {

    private ConnectionOptions connectionOptions;
    private String valueOfProp;

    public JdbcOption(final ConnectionOptions connectionOptions,
                      final String valueOfProp) {
        this.connectionOptions = connectionOptions;
        this.valueOfProp = valueOfProp;
    }

    public ConnectionOptions getConnectionOptions() {
        return connectionOptions;
    }

    public String getValueOfProp() {
        return valueOfProp;
    }
}
