package com.example.demo.domain.document;

import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
@Table(name = "documents")
@Entity(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "URL is required")
    private String url;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
