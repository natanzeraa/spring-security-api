package com.example.demo.domain.user;

public record UserRegisterDTO(String login, String password, UserRole role) {
}
