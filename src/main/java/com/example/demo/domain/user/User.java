package com.example.demo.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "users")
@Entity(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Login is required")
    @Email(message = "Must be a valid email")
    private String login;

    @NotNull(message = "Password is required")
    private String password;

    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User(){}

    public User(String login, String encryptedPassword, UserRole role) {
        this.login = login;
        this.password = encryptedPassword;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRole = this.role.name();

        if(this.role == UserRole.ADMIN)
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER"),
                    new SimpleGrantedAuthority("ROLE_VIEWER")
            );
        else if (this.role == UserRole.USER) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_USER"),
                    new SimpleGrantedAuthority("ROLE_VIEWER")
            );
        }
        else {
            return List.of(new SimpleGrantedAuthority( "ROLE_" + userRole));
        }
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
