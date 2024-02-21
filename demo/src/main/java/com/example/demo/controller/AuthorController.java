package com.example.demo.controller;

import com.example.demo.dto.AuthorDto;
import com.example.demo.service.AuthorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "AuthorController")
@RequestMapping("/api/author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/addAuthor")
    public ResponseEntity<Long> addAuthor(@Valid @RequestBody AuthorDto authorDto) {
        Long authorId = authorService.addAuthor(authorDto);
        return new ResponseEntity<>(authorId, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAuthor/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){ authorService.deleteAuthorById(id); return new ResponseEntity<>(HttpStatus.OK); }
}