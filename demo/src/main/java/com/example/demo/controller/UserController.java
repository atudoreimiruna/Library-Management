package com.example.demo.controller;

import com.example.demo.dto.UserBorrowingDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "UserController")
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUsers")
    @Transactional
    public ResponseEntity<String> addUsers(@Valid @RequestBody List<User> users) {
        return userService.addUsers(users);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsersWithBorrowedBooks() {
        return new ResponseEntity<>(userService.getAllUsersWithBorrowedBooks(), HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserBorrowingDto> getUser(@PathVariable(name = "id") Long id) {
        return userService.getUserWithBorrowings(id);
    }
}