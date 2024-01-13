package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.BookAuthor;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @PostMapping("/addBooks")
    public ResponseEntity<String> addBooks(@RequestBody List<BookDto> bookDtoList) {
        try {
            for (BookDto bookDto : bookDtoList) {
                Author author = authorService.findAuthorByName(bookDto.getAuthorName());

                if (author == null) {
                    author = new Author();
                    author.setName(bookDto.getAuthorName());
                    authorService.saveAuthor(author);
                }

                Book book = Book.builder()
                        .title(bookDto.getTitle())
                        .isbn(bookDto.getIsbn())
                        .quantity(bookDto.getQuantity())
                        .build();

                BookAuthor bookAuthor = new BookAuthor();
                bookAuthor.setDateOfWriting(bookDto.getDateOfWriting());
                bookAuthor.setBook(book);
                bookAuthor.setAuthor(author);

                bookService.saveBook(book);
            }

            return new ResponseEntity<>("Books added successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding books: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
