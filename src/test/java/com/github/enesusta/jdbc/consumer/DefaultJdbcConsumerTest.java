package com.github.enesusta.jdbc.consumer;

import com.github.enesusta.jdbc.datasource.JdbcConfiguration;
import com.github.enesusta.jdbc.datasource.JdbcDataSource;
import com.github.enesusta.jdbc.datasource.HikariJdbcDataSource;
import com.github.enesusta.jdbc.domain.Humans;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.util.Set;

class DefaultJdbcConsumerTest {


    private JdbcDataSource jdbcDataSource;
    private DataSource dataSource;

    @BeforeEach
    public void init() {

        JdbcConfiguration configuration = new JdbcConfiguration.JdbcConfigurationBuilder()
                .username("docker")
                .password("mykenai1363")
                .driverClassName("org.postgresql.Driver")
                .jdbcUrl("jdbc:postgresql://localhost:5432/docker?characterEncoding=utf8")
                .build();

        jdbcDataSource = new HikariJdbcDataSource();
        dataSource = jdbcDataSource.getDataSource(configuration);

    }


    @Test
    void shouldValidateQueryReturn() {

        JdbcConsumer<Humans> humansJdbcConsumer = new DefaultJdbcConsumer<>(dataSource);
        Set<Humans> humansSet = humansJdbcConsumer.retrieveAll(Humans.class);

        humansSet.forEach(e -> {
            System.out.printf("ID is : %d, first_name is %s\n", e.getId(), e.getFirstName());
        });
    }

}