package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBooksDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private List<BookViewDto> borrowedBooks;
}