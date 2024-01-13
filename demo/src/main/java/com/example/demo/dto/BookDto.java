package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookDto {
    private String title;
    private String isbn;
    private int quantity;
    private String authorName;
    private LocalDate dateOfWriting;
}
