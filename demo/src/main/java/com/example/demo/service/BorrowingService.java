package com.example.demo.service;

import com.example.demo.model.Borrowing;
import com.example.demo.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowingService {
    @Autowired
    private BorrowingRepository borrowingRepository;
    public void saveBorrowing(Borrowing borrowing) {
        borrowingRepository.save(borrowing);
    }

}
