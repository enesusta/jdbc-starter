package com.github.enesusta.jdbc.consumer.query;

public interface QueryBuilder {
    String getQuery(String[] queryElements, String from);
}
