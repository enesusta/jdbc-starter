package com.github.enesusta.jdbc.consumer;

import com.github.enesusta.jdbc.consumer.query.DefaultQueryBuilder;
import com.github.enesusta.jdbc.consumer.query.QueryBuilder;
import com.github.enesusta.jdbc.reflection.Column;
import com.github.enesusta.jdbc.reflection.Domain;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DefaultJdbcConsumer<T> implements JdbcConsumer<T> {

    private final DataSource dataSource;
    private QueryBuilder queryBuilder;

    public DefaultJdbcConsumer(final DataSource dataSource) {
        this.dataSource = dataSource;
        this.queryBuilder = new DefaultQueryBuilder();
    }

    /**
     * Query'i olustursun.
     * Bu degerler ile; objeleri olusturup Set halinde return etsin.
     */

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Set<T> retrieveAll(final Class klass) {

        final Domain from = (Domain) klass.getAnnotation(Domain.class);
        final Field[] fields = klass.getDeclaredFields();
        final String[] queryElement = new String[fields.length];

        byte counter = 0;
        for (Field field : fields) {
            field.setAccessible(true);
            Column column = field.getAnnotation(Column.class);
            if (column != null)
                queryElement[counter] = column.value();
            counter++;
        }

        final String query = queryBuilder.getQuery(queryElement, from.value());
        System.out.println("query = " + query);

        return new HashSet<>();
    }
}
