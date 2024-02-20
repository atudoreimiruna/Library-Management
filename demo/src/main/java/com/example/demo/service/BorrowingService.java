package com.example.demo.service;

import com.example.demo.dto.BorrowingInfoDto;
import com.example.demo.model.Book;
import com.example.demo.model.Borrowing;
import com.example.demo.model.User;
import com.example.demo.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowingService {
    private final UserService userService;
    private final BookService bookService;
    private final BorrowingRepository borrowingRepository;

    @Autowired
    public BorrowingService(UserService userService, BookService bookService, BorrowingRepository borrowingRepository) {
        this.userService = userService;
        this.bookService = bookService;
        this.borrowingRepository = borrowingRepository;
    }

    public ResponseEntity<String> addBorrowings(Long userId, List<BorrowingInfoDto> borrowingInfoList) {
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

                saveBorrowing(borrowing);
            }

            return new ResponseEntity<>("Borrowings added successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding borrowings: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void saveBorrowing(Borrowing borrowing) {
        borrowingRepository.save(borrowing);
    }
}
