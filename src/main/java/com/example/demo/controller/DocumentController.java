package com.example.demo.controller;

import com.example.demo.domain.document.Document;
import com.example.demo.domain.document.DocumentDTO;
import com.example.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("documents")
public class DocumentController {
    @Autowired
    DocumentService service;

    @GetMapping
    public ResponseEntity<List<Document>> getAllDocuments() {
        return ResponseEntity.ok().body(this.service.listAllDocuments());
    }

    @PostMapping
    public ResponseEntity<Document> uploadNewDocument(@RequestBody DocumentDTO data) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.uploadDocument(data));
    }
}
