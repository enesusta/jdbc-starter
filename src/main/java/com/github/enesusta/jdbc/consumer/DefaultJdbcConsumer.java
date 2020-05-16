package com.github.enesusta.jdbc.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.Iterator;
import java.util.Set;

public class DefaultJdbcConsumer<T> implements JdbcConsumer<T> {

    private final static Logger logger = LoggerFactory.getLogger(DefaultJdbcConsumer.class);
    private final RowMapper<T> rowMapper;

    public DefaultJdbcConsumer(final DataSource dataSource) {
        this.rowMapper = new DefaultRowMapper<>(dataSource);
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
        return rowMapper.rowMapper(klass);
    }
}
