package com.example.demo.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
@Table(name = "users")
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Login is required")
    @Email(message = "Must be a valid email")
    private String login;

    @NotNull(message = "Password is required")
    private String password;

    @NotNull(message = "Role is required")
    private UserRole role;

}
