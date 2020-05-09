package com.github.enesusta.jdbc.consumer;

import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

public interface JdbcConsumer<T> extends Iterable<T> {

    default void consume(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this)
            action.accept(t);
    }

    Set<T> retrieveAll(Class clazz);

}
