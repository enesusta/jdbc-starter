package com.github.enesusta.jdbc.consumer;

import javax.sql.DataSource;
import java.util.Set;

public interface RowMapper<T> {
    Set<T> rowMapper(Class<T> klass);
}
