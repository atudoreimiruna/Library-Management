package com.example.demo.dto;

import java.util.List;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}