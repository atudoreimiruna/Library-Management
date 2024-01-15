package com.example.demo.controller;

import com.example.demo.dto.BookViewDto;
import com.example.demo.dto.BorrowingDto;
import com.example.demo.dto.UserBorrowingDto;
import com.example.demo.model.Borrowing;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "UserController")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/saveUsers")
    @Transactional
    public String saveUsers(@Valid @RequestBody List<User> users) {
        System.out.println("Users save called...");

        List<User> savedUsers = userRepository.saveAll(users);
        System.out.println("Users out :: " + savedUsers);

        System.out.println("Saved!!!");
        return "Users saved!!!";
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsersWithBorrowedBooks() {
        return new ResponseEntity<>(userService.getAllUsersWithBorrowedBooks(), HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserBorrowingDto> getUser(@PathVariable(name = "id") Long id) {
        try {
            User user = userRepository.getById(id);

            List<BorrowingDto> borrowingDtos = user.getBorrowing().stream()
                    .map(this::convertToBorrowingDto)
                    .collect(Collectors.toList());

            UserBorrowingDto userDto = new UserBorrowingDto();
            userDto.setUserId(user.getId());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            userDto.setBorrowings(borrowingDtos);

            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private BorrowingDto convertToBorrowingDto(Borrowing borrowing) {
        BorrowingDto borrowingDto = new BorrowingDto();
        borrowingDto.setBorrowingId(borrowing.getId());
        borrowingDto.setBorrowDate(borrowing.getBorrowDate());
        borrowingDto.setReturnDate(borrowing.getReturnDate());
        borrowingDto.setFineAmount(borrowing.getFineAmount());

        BookViewDto bookDto = new BookViewDto();
        bookDto.setBookId(borrowing.getBook().getId());
        bookDto.setTitle(borrowing.getBook().getTitle());
        bookDto.setIsbn(borrowing.getBook().getIsbn());

        borrowingDto.setBook(bookDto);

        return borrowingDto;
    }
}