package com.example.demo.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("The book does not exist.");
    }
}