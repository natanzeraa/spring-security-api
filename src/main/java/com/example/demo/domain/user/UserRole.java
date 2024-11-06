package com.example.demo.domain.user;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("admin"),
    VIEWER("viewer"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

}
