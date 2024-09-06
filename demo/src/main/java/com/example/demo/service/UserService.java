package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.Book;
import com.example.demo.model.Borrowing;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<String> addUsers(List<User> usersList) {
        try {
            userRepository.saveAll(usersList);

            return new ResponseEntity<>("Books added successfully", HttpStatus.OK);
        } catch (
                Exception e) {
            return new ResponseEntity<>("Error adding books: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void deleteUserById(Long id) {
        boolean exists = userRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("User with id: "+ id +" doesn't exist");
        }
        userRepository.deleteById(id);
    }

    public Long updateUser(UserPutDto userDto) {
        boolean exists = userRepository.existsById(userDto.getId());
        if(!exists){
            throw new IllegalStateException("User with id: "+ userDto.getId() +" doesn't exist");
        }
        User newUser = mapUserDtoToUser(userDto);
        return userRepository.save(newUser).getId();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public ResponseEntity<UserBorrowingDto> getUserWithBorrowings(Long id) {
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

    private UserBooksDto fromUser(Borrowing borrowing) {
        UserBooksDto borrowingDto = new UserBooksDto();
        return borrowingDto;
    }

    private User mapUserDtoToUser(UserPutDto userDto){
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();
    }

}
