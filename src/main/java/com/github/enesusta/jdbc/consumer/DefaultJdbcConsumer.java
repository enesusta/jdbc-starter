package com.github.enesusta.jdbc.consumer;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Set;

public class DefaultJdbcConsumer<T> implements JdbcConsumer<T> {


    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Set<T> retrieveAll(Class clazz) {

        final Field[] fields = clazz.getDeclaredFields();


        return null;
    }
}
