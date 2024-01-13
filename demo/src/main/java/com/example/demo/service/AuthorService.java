package com.example.demo.service;

import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author findAuthorByName(String name) {
        return authorRepository.findByName(name);
    }

    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }
}