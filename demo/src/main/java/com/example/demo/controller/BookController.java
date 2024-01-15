package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.BookAuthor;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookAuthorService;
import com.example.demo.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "BookController")
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookAuthorService bookAuthorService;

    @Autowired
    private AuthorService authorService;

    @PostMapping("/addBooks")
//    @Operation(summary = "", description = "")
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

                bookService.saveBook(book);

                BookAuthor bookAuthor = new BookAuthor();
                bookAuthor.setDateOfWriting(bookDto.getDateOfWriting());
                bookAuthor.setBook(book);
                bookAuthor.setAuthor(author);

                bookAuthorService.saveBookAuthor(bookAuthor);
            }

            return new ResponseEntity<>("Books added successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding books: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
