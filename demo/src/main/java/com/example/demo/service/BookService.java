package com.example.demo.service;

import com.example.demo.dto.AvailableBookDto;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void saveBook(Book book) {
        bookRepository.save(book);
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