package com.example.demo.service;

import com.example.demo.model.BookAuthor;
import com.example.demo.repository.BookAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookAuthorService {
    @Autowired
    private BookAuthorRepository bookAuthorRepository;

    public void saveBookAuthor(BookAuthor bookAuthor) {
        bookAuthorRepository.save(bookAuthor);
    }
}
