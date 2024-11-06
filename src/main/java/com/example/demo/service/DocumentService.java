package com.example.demo.service;

import com.example.demo.domain.document.Document;
import com.example.demo.domain.document.DocumentDTO;
import com.example.demo.domain.user.User;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    DocumentRepository repository;

    @Autowired
    UserRepository userRepository;

    public List<Document> listAllDocuments() {
        return this.repository.findAll();
    }

    public Document uploadDocument(DocumentDTO data) throws Exception {
        User user = userRepository.findById(data.user())
                .orElseThrow(() -> new Exception("User not found."));

        Document newDoc = new Document();
        newDoc.setTitle(data.title());
        newDoc.setUser(user);
        newDoc.setUrl(data.url());

        return this.repository.save(newDoc);
    }
}
