package com.github.enesusta.jdbc.consumer.query;

public final class DefaultQueryBuilder implements QueryBuilder {

    @Override
    public final String getQuery(final String[] queryElements, final String from) {

        StringBuffer buffer = new StringBuffer();
        buffer.append("select ");

        for (final String queryElement : queryElements)
            buffer.append(String.format("%s,", queryElement));

        buffer.deleteCharAt(buffer.lastIndexOf(","));
        buffer.append(' ');
        buffer.append(String.format("from %s", from));

        return buffer.toString();
    }
}
