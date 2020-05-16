package com.github.enesusta.jdbc.domain;

import com.github.enesusta.jdbc.reflection.Column;
import com.github.enesusta.jdbc.reflection.Domain;

@Domain("humans")
public class Humans {

    @Column("id")
    private int id;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("email")
    private String email;

    @Column("ip_address")
    private String ipAddress;

}
