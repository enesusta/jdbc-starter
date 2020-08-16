[![javadoc](https://javadoc.io/badge2/com.github.enesusta/jdbc-starter/javadoc.svg)](https://javadoc.io/doc/com.github.enesusta/jdbc-starter)

## Install

```xml
<dependency>
  <groupId>com.github.enesusta</groupId>
  <artifactId>jdbc-starter</artifactId>
  <version>1.0.5</version>
</dependency>
```


Example:
```java
JdbcConfiguration configuration = new JdbcConfiguration.JdbcConfigurationBuilder()
            .username("username")
            .password("password")
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

```