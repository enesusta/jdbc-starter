package com.github.enesusta.jdbc.consumer.reflection;

import com.github.enesusta.jdbc.reflection.Column;

import java.lang.reflect.Field;

public final class DefaultReflectionConsumer implements ReflectionConsumer {

    @Override
    public final String[] getQueryElements(final Class klass) {

        final Field[] fields = klass.getDeclaredFields();
        final String[] queryElements = new String[fields.length];

        byte counter = 0;
        for (Field field : fields) {
            field.setAccessible(true);
            Column column = field.getAnnotation(Column.class);
            if (column != null)
                queryElements[counter] = column.value();
            counter++;
        }

        return queryElements;
    }
}
