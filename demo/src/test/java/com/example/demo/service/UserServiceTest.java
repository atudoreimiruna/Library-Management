package com.example.demo.service;

import com.example.demo.dto.UserBorrowingDto;
import com.example.demo.dto.UserPutDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    public void testAddUsers_Success() {
        // Arrange
        List<User> usersList = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        usersList.add(user);
        // Act
        ResponseEntity<String> response = userService.addUsers(usersList);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Books added successfully", response.getBody());
        verify(userRepository, times(1)).saveAll(usersList);
    }

    @Test
    public void testDeleteUserById_UserExists() {
        // Arrange
        when(userRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(userRepository).deleteById(anyLong());
        // Act
        userService.deleteUserById(1L);
        // Assert
        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void testDeleteUserById_UserDoesNotExist() {
        // Arrange
        when(userRepository.existsById(anyLong())).thenReturn(false);
        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            userService.deleteUserById(1L);
        });
        String expectedMessage = "User with id: 1 doesn't exist";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(userRepository, never()).deleteById(anyLong());
    }

    @Test
    public void testUpdateUser_UserExists() {
        // Arrange
        UserPutDto userDto = new UserPutDto();
        userDto.setId(1L);
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        when(userRepository.existsById(anyLong())).thenReturn(true);
        when(userRepository.save(any(User.class))).thenReturn(user);
        // Act
        Long result = userService.updateUser(userDto);
        // Assert
        assertNotNull(result);
        assertEquals(1L, result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testUpdateUser_UserDoesNotExist() {
        // Arrange
        UserPutDto userDto = new UserPutDto();
        userDto.setId(1L);
        when(userRepository.existsById(anyLong())).thenReturn(false);
        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            userService.updateUser(userDto);
        });
        String expectedMessage = "User with id: 1 doesn't exist";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testFindUserById_UserExists() {
        // Arrange
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        // Act
        User result = userService.findUserById(1L);
        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testFindUserById_UserDoesNotExist() {
        // Arrange
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        // Act
        User result = userService.findUserById(1L);
        // Assert
        assertNull(result);
        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testGetUserWithBorrowings_UserDoesNotExist() {
        // Arrange
        when(userRepository.getById(anyLong())).thenThrow(new EntityNotFoundException());
        // Act
        ResponseEntity<UserBorrowingDto> response = userService.getUserWithBorrowings(1L);
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userRepository, times(1)).getById(anyLong());
    }
}
