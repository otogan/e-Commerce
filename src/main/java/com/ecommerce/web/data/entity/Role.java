package com.ecommerce.web.data.entity;

import javax.persistence.Column;

public class Role {
    @Column(name = "ROLE", columnDefinition = "varchar(50) default 'USER'")
    private String role;
}
