package com.example.demo.controller;

import com.example.demo.dto.UserBorrowingDto;
import com.example.demo.dto.UserPutDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "add users", description = "POST method for adding a list of new users")
    public ResponseEntity<String> addUsers(@Valid @RequestBody List<User> users) {
        return userService.addUsers(users);
    }

    @PutMapping("/updateUser")
    @Operation(summary = "update user", description = "PUT method for updating the informations of an user")
    public  ResponseEntity<?> updateUser(@Valid @RequestBody UserPutDto userDto) { return new ResponseEntity<>(userService.updateUser(userDto), HttpStatus.OK);}

    @GetMapping("/getUser/{id}")
    @Operation(summary = "get user", description = "GET method for receiving an user by id")
    public ResponseEntity<UserBorrowingDto> getUser(@PathVariable(name = "id") Long id) {
        return userService.getUserWithBorrowings(id);
    }

    @DeleteMapping("/deleteUser/{id}")
    @Operation(summary = "delete user", description = "DELETE method for removing an user by id")
    public ResponseEntity<?> delete(@PathVariable Long id){ userService.deleteUserById(id); return new ResponseEntity<>(HttpStatus.OK); }
}