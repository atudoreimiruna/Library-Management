package com.example.demo.dto;

import com.example.demo.model.Book;

public class BookViewDto {

    private Long bookId;
    private String title;
    private String isbn;

    public BookViewDto() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // convert Book entity to DTO
    public static BookViewDto fromBook(Book book) {
        BookViewDto dto = new BookViewDto();
        dto.setBookId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        return dto;
    }
}