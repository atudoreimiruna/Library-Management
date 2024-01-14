package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BorrowingInfoDto {
    private Long bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private BigDecimal fineAmount;

    public BorrowingInfoDto() {
    }

    public BorrowingInfoDto(Long bookId, LocalDate borrowDate, LocalDate returnDate, BigDecimal fineAmount) {
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.fineAmount = fineAmount;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
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
}
