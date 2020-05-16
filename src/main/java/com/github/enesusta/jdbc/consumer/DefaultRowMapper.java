package com.github.enesusta.jdbc.consumer;

import com.github.enesusta.jdbc.consumer.query.DefaultQueryBuilder;
import com.github.enesusta.jdbc.consumer.query.QueryBuilder;
import com.github.enesusta.jdbc.reflection.Column;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DefaultRowMapper<T> implements RowMapper<T> {

    private final QueryBuilder queryBuilder;
    private final DataSource dataSource;
    private final static Logger logger = LoggerFactory.getLogger(DefaultRowMapper.class);

    public DefaultRowMapper(final DataSource dataSource) {
        this.dataSource = dataSource;
        this.queryBuilder = new DefaultQueryBuilder();
    }

    @Override
    public final Set<T> rowMapper(final Class<T> klass) {

        final String query = queryBuilder.getQuery(klass);
        final Field[] fields = klass.getDeclaredFields();
        final Set<T> hashSet = new HashSet<>(20);

        try (Connection connection =
                     dataSource.getConnection()) {

            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(query)) {

                final ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    T instance = klass.newInstance();
                    for (final Field field : fields) {
                        field.setAccessible(true);
                        Column column = field.getAnnotation(Column.class);
                        final Object value = resultSet.getObject(column.value(), field.getType());
                        field.set(instance, value);
                    }
                    hashSet.add(instance);
                }

                resultSet.close();

            } catch (SQLException | IllegalAccessException | InstantiationException e) {
                logger.error(e.getMessage());
                connection.rollback();
            }

            connection.commit();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return hashSet;
    }
}
