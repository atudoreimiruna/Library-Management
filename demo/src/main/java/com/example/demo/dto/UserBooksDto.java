package com.example.demo.dto;

import com.example.demo.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserBooksDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private List<BookViewDto> borrowedBooks;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<BookViewDto> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<BookViewDto> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public static UserBooksDto fromUser(User user) {
        UserBooksDto dto = new UserBooksDto();
        dto.setUserId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());

        List<BookViewDto> bookDTOs = user.getBorrowing().stream()
                .map(borrowing -> BookViewDto.fromBook(borrowing.getBook()))
                .collect(Collectors.toList());

        dto.setBorrowedBooks(bookDTOs);
        return dto;
    }
}