package com.example.demo.controller;

import com.example.demo.dto.BorrowingInfoDto;
import com.example.demo.exception.BookBorrowedException;
import com.example.demo.service.BorrowingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "BorrowingController")
@RequestMapping("/api/borrowing")
public class BorrowingController {
    private final BorrowingService borrowingService;

    @Autowired
    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @PostMapping("/addBorrowings/{userId}")
    public ResponseEntity<String> addBorrowings(@PathVariable Long userId, @RequestBody List<BorrowingInfoDto> borrowingInfoList) {
        return borrowingService.addBorrowings(userId, borrowingInfoList);
    }

    @ExceptionHandler(BookBorrowedException.class)
    public ResponseEntity<String> handleBookBorrowedException(BookBorrowedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
