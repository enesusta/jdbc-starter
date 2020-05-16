package com.github.enesusta.jdbc.consumer;

import com.github.enesusta.jdbc.consumer.query.DefaultQueryBuilder;
import com.github.enesusta.jdbc.consumer.query.QueryBuilder;
import com.github.enesusta.jdbc.consumer.reflection.DefaultReflectionConsumer;
import com.github.enesusta.jdbc.consumer.reflection.ReflectionConsumer;
import com.github.enesusta.jdbc.reflection.Column;
import com.github.enesusta.jdbc.reflection.Domain;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DefaultJdbcConsumer<T> implements JdbcConsumer<T> {

    private final DataSource dataSource;
    private final QueryBuilder queryBuilder;
    private final ReflectionConsumer reflectionConsumer;

    public DefaultJdbcConsumer(final DataSource dataSource) {
        this.dataSource = dataSource;
        this.queryBuilder = new DefaultQueryBuilder();
        this.reflectionConsumer = new DefaultReflectionConsumer();
    }

    /**
     * Query'i olustursun. --yaptik--
     * Bu degerler ile; objeleri olusturup Set halinde return etsin.
     */

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Set<T> retrieveAll(final Class<T> klass) {

        final Domain from = klass.getAnnotation(Domain.class);
        final String[] queryElements = reflectionConsumer.getQueryElements(klass);
        final String query = queryBuilder.getQuery(queryElements, from.value());
        final Field[] fields = klass.getDeclaredFields();
        final Set<T> set = new HashSet<>(20);

        try (Connection connection =
                     dataSource.getConnection()) {

            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(query + " LIMIT 10")) {

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    T t = klass.newInstance();
                    for (final Field field : fields) {
                        field.setAccessible(true);
                        Column column = field.getAnnotation(Column.class);
                        final Object value = rs.getObject(column.value(), field.getType());
                        field.set(t, value);
                    }
                    set.add(t);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return set;
    }
}
