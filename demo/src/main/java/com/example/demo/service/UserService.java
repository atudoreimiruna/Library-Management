package com.example.demo.service;

import com.example.demo.dto.UserBooksDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserBooksDto> getAllUsersWithBorrowedBooks() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserBooksDto::fromUser)
                .collect(Collectors.toList());
    }
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
