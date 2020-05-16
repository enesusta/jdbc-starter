package com.github.enesusta.jdbc.consumer.query;

public interface QueryBuilder {
    <T> String getQuery(Class<T> klass);
}
