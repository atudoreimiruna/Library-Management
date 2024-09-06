package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.enums.BorrowingStatusEnum;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book testBook;

    @BeforeEach
    void setUp() {
        testBook = new Book();
        testBook.setId(1L);
        testBook.setTitle("Test Book 1");
        testBook.setIsbn("636718176");
        testBook.setQuantity(3);
        testBook.setStatus(BorrowingStatusEnum.AVAILABLE);
    }

    @Test
    public void testAddBook_Success() {
        // Arrange
        BookDto bookDto = new BookDto("Title 2", "ISBN5678", 10, "Author 2", LocalDate.now());
        Book book = new Book();
        book.setId(1L);
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        // Act
        Long result = bookService.addBook(bookDto);
        // Assert
        assertNotNull(result);
        assertEquals(1L, result);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void testDeleteBookById_BookExists() {
        // Arrange
        when(bookRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(bookRepository).deleteById(anyLong());
        // Act
        bookService.deleteBookById(1L);
        // Assert
        verify(bookRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void testDeleteBookById_BookNotFound() {
        // Arrange
        when(bookRepository.existsById(anyLong())).thenReturn(false);
        // Act & Assert
        assertThrows(BookNotFoundException.class, () -> bookService.deleteBookById(1L));
    }

    @Test
    public void testFindBookById_BookExists() {
        // Arrange
        Book book = new Book();
        book.setId(1L);
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        // Act
        Book result = bookService.findBookById(1L);
        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testFindBookById_BookDoesNotExist() {
        // Arrange
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
        // Act
        Book result = bookService.findBookById(1L);
        // Assert
        assertNull(result);
    }
}

