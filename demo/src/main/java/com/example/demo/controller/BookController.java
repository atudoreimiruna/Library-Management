package com.example.demo.controller;

import com.example.demo.dto.AvailableBookDto;
import com.example.demo.dto.BookDto;
import com.example.demo.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "BookController")
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addBooks")
    @Operation(summary = "add books", description = "post method for adding a list of books")
    public ResponseEntity<String> addBooks(@RequestBody List<BookDto> bookDtoList) {
        return bookService.addBooks(bookDtoList);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Long> addBook(@Valid @RequestBody BookDto bookDto) {
        Long bookId = bookService.addBook(bookDto);
        return new ResponseEntity<>(bookId, HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<AvailableBookDto>> getAvailableBooks() {
        List<AvailableBookDto> availableBooks = bookService.getAvailableBooks();
        return ResponseEntity.ok(availableBooks);
    }
}
