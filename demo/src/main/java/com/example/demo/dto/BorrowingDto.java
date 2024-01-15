package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BorrowingDto {
    private Long borrowingId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private BigDecimal fineAmount;
    private BookViewDto book;

    public Long getBorrowingId() {
        return borrowingId;
    }

    public void setBorrowingId(Long borrowingId) {
        this.borrowingId = borrowingId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BigDecimal getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(BigDecimal fineAmount) {
        this.fineAmount = fineAmount;
    }

    public BookViewDto getBook() {
        return book;
    }

    public void setBook(BookViewDto book) {
        this.book = book;
    }
}