package com.example.demo.controller;

import com.example.demo.dto.BorrowingInfoDto;
import com.example.demo.model.Book;
import com.example.demo.model.Borrowing;
import com.example.demo.model.User;
import com.example.demo.service.BookService;
import com.example.demo.service.BorrowingService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowing")
public class BorrowingController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowingService borrowingService;

    @PostMapping("/addBorrowings/{userId}")
    public ResponseEntity<String> addBorrowings(@PathVariable Long userId, @RequestBody List<BorrowingInfoDto> borrowingInfoList) {
        try {
            User user = userService.findUserById(userId);

            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            for (BorrowingInfoDto borrowingInfo : borrowingInfoList) {
                Book book = bookService.findBookById(borrowingInfo.getBookId());

                if (book == null) {
                    return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
                }

                Borrowing borrowing = new Borrowing();
                borrowing.setBorrowDate(borrowingInfo.getBorrowDate());
                borrowing.setReturnDate(borrowingInfo.getReturnDate());
                borrowing.setFineAmount(borrowingInfo.getFineAmount());
                borrowing.setUser(user);
                borrowing.setBook(book);

                borrowingService.saveBorrowing(borrowing);
            }

            return new ResponseEntity<>("Borrowings added successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding borrowings: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
