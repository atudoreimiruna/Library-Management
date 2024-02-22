package com.example.demo.repository;

import com.example.demo.enums.BorrowingStatusEnum;
import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findById(Long id);
    List<Book> findByStatus(BorrowingStatusEnum status);
}