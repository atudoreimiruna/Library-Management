package com.example.demo.service;

import com.example.demo.dto.AvailableBookDto;
import com.example.demo.dto.BookDto;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.BookAuthor;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final BookAuthorService bookAuthorService;
    @Autowired
    public BookService(BookRepository bookRepository,
                       AuthorService authorService,
                       BookAuthorService bookAuthorService
    ) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.bookAuthorService = bookAuthorService;
    }

    public ResponseEntity<String> addBooks(List<BookDto> bookDtoList) {
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

                bookRepository.save(book);

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

    public Long addBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setIsbn(bookDto.getIsbn());
        book.setQuantity(bookDto.getQuantity());

        Book savedBook = bookRepository.save(book);

        return savedBook.getId();
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<AvailableBookDto> getAvailableBooks() {
        LocalDate currentDate = LocalDate.now();

        List<Book> availableBooks = bookRepository.findAll().stream()
                .filter(book -> isBookAvailable(book, currentDate))
                .collect(Collectors.toList());

        return mapToDto(availableBooks);
    }

    private boolean isBookAvailable(Book book, LocalDate currentDate) {
        return book.getBorrowing().stream()
                .noneMatch(borrowing -> borrowing.getBorrowDate().isAfter(currentDate)
                        || (borrowing.getReturnDate() != null && borrowing.getReturnDate().isBefore(currentDate)));
    }

    private List<AvailableBookDto> mapToDto(List<Book> books) {
        return books.stream()
                .map(book -> new AvailableBookDto(
                        book.getId(),
                        book.getTitle(),
                        book.getIsbn(),
                        book.getQuantity()
                ))
                .collect(Collectors.toList());
    }
}