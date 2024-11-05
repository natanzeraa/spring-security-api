package com.example.demo.domain.document;

import java.util.UUID;

public record DocumentDTO(String title, String url, UUID user) {
}
