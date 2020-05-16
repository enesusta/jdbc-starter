package com.github.enesusta.jdbc.test;

import com.github.enesusta.jdbc.datasource.JdbcConfiguration;
import com.github.enesusta.jdbc.datasource.JdbcDataSource;
import com.github.enesusta.jdbc.datasource.PostgreJdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestOne {

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

        jdbcDataSource = new PostgreJdbcDataSource();
        dataSource = jdbcDataSource.getDataSource(configuration);

    }

    @Test
    public void connection_test() {

        try (Connection connection =
                     dataSource.getConnection()) {

            final String query = "select id,first_name,last_name,ip_address,email from humans";

            try (PreparedStatement prst =
                         connection.prepareStatement(query)) {

                ResultSet rs = prst.executeQuery();

                while (rs.next()) {
                    System.out.println(rs.getInt(1));
                    System.out.println(rs.getString(2));
                }

                rs.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
