package com.example.demo.exception;

public class BookBorrowedException extends RuntimeException{
    public BookBorrowedException() {
        super("The book is already borrowed!");
    }
}
