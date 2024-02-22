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
public class AvailableBookDto {
    private Long id;
    private String title;
    private String isbn;
    private int quantity;
    private List<String> authors;

    public AvailableBookDto(Long id, String title, String isbn, int quantity) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.quantity = quantity;
    }
}