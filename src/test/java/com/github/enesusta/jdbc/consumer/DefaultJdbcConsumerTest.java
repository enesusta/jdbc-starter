package com.github.enesusta.jdbc.consumer;

import com.github.enesusta.jdbc.datasource.JdbcConfiguration;
import com.github.enesusta.jdbc.datasource.JdbcDataSource;
import com.github.enesusta.jdbc.datasource.HikariJdbcDataSource;
import com.github.enesusta.jdbc.datasource.JdbcOption;
import com.github.enesusta.jdbc.datasource.enums.ConnectionOptions;
import com.github.enesusta.jdbc.datasource.enums.DatabaseType;
import com.github.enesusta.jdbc.domain.Humans;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

class DefaultJdbcConsumerTest {


    private JdbcDataSource jdbcDataSource;
    private DataSource dataSource;

    @BeforeEach
    public void init() {

        JdbcConfiguration configuration = new JdbcConfiguration.JdbcConfigurationBuilder()
            .username("docker")
            .password("mykenai1363")
            .type(DatabaseType.POSTGRE)
            .host("localhost")
            .selectedDatabase("docker")
            .options(
                Arrays.asList(new JdbcOption(ConnectionOptions.CHARACTER_ENCODING, "utf8"))
            )
//            .jdbcUrl("jdbc:postgresql://localhost:5432/docker?characterEncoding=utf8")
            .build();

        jdbcDataSource = new HikariJdbcDataSource(configuration);
        dataSource = jdbcDataSource.getDataSource();

    }


    @Test
    void shouldValidateQueryReturn() {

        JdbcConsumer<Humans> humansJdbcConsumer = new DefaultJdbcConsumer<>(dataSource);
        Set<Humans> humansSet = humansJdbcConsumer.retrieveAll(Humans.class);

       // humansSet.forEach(e -> {
        //    System.out.printf("ID is : %d, first_name is %s\n", e.getId(), e.getFirstName());
        //});
    }

}
