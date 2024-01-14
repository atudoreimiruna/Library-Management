package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/saveUsers")
    @Transactional
    public String saveUsers(@RequestBody List<User> users) {
        System.out.println("Users save called...");

        List<User> savedUsers = userRepository.saveAll(users);
        System.out.println("Users out :: " + savedUsers);

        System.out.println("Saved!!!");
        return "Users saved!!!";
    }

    @GetMapping("/getUser/{id}")
    public String getUser(@PathVariable(name = "id") String id) {
        System.out.println("User get called...");

        User userOut = userRepository.getById(Long.valueOf(id));
        System.out.println("\nUser details :: \n" + userOut);

        System.out.println("\nDone!!!");
        return "User fetched...";
    }
}