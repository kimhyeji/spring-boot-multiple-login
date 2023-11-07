package com.mysite.sbb.user;

import lombok.Getter;


@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    LAWYER("ROLE_LAWYER"),
    USER("ROLE_USER");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}