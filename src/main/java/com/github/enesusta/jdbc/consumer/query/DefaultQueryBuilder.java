package com.github.enesusta.jdbc.consumer.query;

import com.github.enesusta.jdbc.consumer.reflection.DefaultReflectionConsumer;
import com.github.enesusta.jdbc.consumer.reflection.ReflectionConsumer;
import com.github.enesusta.jdbc.reflection.Domain;

public final class DefaultQueryBuilder implements QueryBuilder {

    private final ReflectionConsumer reflectionConsumer;

    public DefaultQueryBuilder() {
        this.reflectionConsumer = new DefaultReflectionConsumer();
    }

    @Override
    public final <T> String getQuery(final Class<T> klass) {

        final Domain from = klass.getAnnotation(Domain.class);
        StringBuilder builder = new StringBuilder();
        builder.append("select ");

        for (final String queryElement : reflectionConsumer.getQueryElements(klass))
            builder.append(String.format("%s,", queryElement));

        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append(' ');
        builder.append(String.format("from %s", from.value()));

        return builder.toString();
    }
}
