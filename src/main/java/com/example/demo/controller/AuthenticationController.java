package com.example.demo.controller;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserAuthenticationDTO;
import com.example.demo.domain.user.UserLoginResponseDTO;
import com.example.demo.domain.user.UserRegisterDTO;
import com.example.demo.infra.config.TokenServiceConfiguration;
import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository repository;

    @Autowired
    TokenServiceConfiguration tokenServiceConfiguration;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserAuthenticationDTO data) {
        UserDetails userDetails = repository.findByLogin(data.login());

        if(userDetails == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if(!encoder.matches(data.password(), userDetails.getPassword()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid password");

        var token = tokenServiceConfiguration.generateToken((User) userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(new UserLoginResponseDTO(token));
    }

    @PostMapping("/hash-generator/{password}")
    public ResponseEntity<Object> hashGenerator(@PathVariable String password) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new BCryptPasswordEncoder().encode(password));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserRegisterDTO data) {
        if(repository.findByLogin(data.login()) != null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User already exists, try to login");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(newUser));
    }
}
