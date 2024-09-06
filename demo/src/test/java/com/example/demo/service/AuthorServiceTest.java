package com.example.demo.service;

import com.example.demo.dto.AuthorDto;
import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
    @Mock
    private AuthorRepository authorRepository;
    @InjectMocks
    private AuthorService authorService;

    @Test
    public void testFindAuthorByName_AuthorExists() {
        // Arrange
        Author author = new Author();
        author.setId(1L);
        author.setName("John Doe");
        when(authorRepository.findByName(anyString())).thenReturn(author);
        // Act
        Author result = authorService.findAuthorByName("John Doe");
        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(authorRepository, times(1)).findByName(anyString());
    }

    @Test
    public void testFindAuthorByName_AuthorDoesNotExist() {
        // Arrange
        when(authorRepository.findByName(anyString())).thenReturn(null);
        // Act
        Author result = authorService.findAuthorByName("Jane Doe");
        // Assert
        assertNull(result);
        verify(authorRepository, times(1)).findByName(anyString());
    }

    @Test
    public void testSaveAuthor_Success() {
        // Arrange
        Author author = new Author();
        author.setName("John Doe");
        when(authorRepository.save(any(Author.class))).thenReturn(author);
        // Act
        authorService.saveAuthor(author);
        // Assert
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    public void testAddAuthor_Success() {
        // Arrange
        AuthorDto authorDto = new AuthorDto();
        authorDto.setName("Jane Doe");
        Author savedAuthor = new Author();
        savedAuthor.setId(1L);
        savedAuthor.setName("Jane Doe");
        when(authorRepository.save(any(Author.class))).thenReturn(savedAuthor);
        // Act
        Long result = authorService.addAuthor(authorDto);
        // Assert
        assertNotNull(result);
        assertEquals(1L, result);
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    public void testDeleteAuthorById_AuthorExists() {
        // Arrange
        when(authorRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(authorRepository).deleteById(anyLong());
        // Act
        authorService.deleteAuthorById(1L);
        // Assert
        verify(authorRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void testDeleteAuthorById_AuthorDoesNotExist() {
        // Arrange
        when(authorRepository.existsById(anyLong())).thenReturn(false);
        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            authorService.deleteAuthorById(1L);
        });
        String expectedMessage = "Author with id: 1 doesn't exist";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(authorRepository, never()).deleteById(anyLong());
    }
}