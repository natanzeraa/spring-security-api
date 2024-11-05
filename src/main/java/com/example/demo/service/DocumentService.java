package com.example.demo.service;

import com.example.demo.domain.document.Document;
import com.example.demo.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    DocumentRepository repository;

    public List<Document> listAllDocuments() {
        return this.repository.findAll();
    }
}
