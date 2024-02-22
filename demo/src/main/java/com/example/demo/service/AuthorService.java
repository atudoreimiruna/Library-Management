package com.example.demo.service;

import com.example.demo.dto.AuthorDto;
import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author findAuthorByName(String name) {
        return authorRepository.findByName(name);
    }

    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    public Long addAuthor(AuthorDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.getName());

        Author savedAuthor = authorRepository.save(author);

        return savedAuthor.getId();
    }

    public void deleteAuthorById(Long id) {
        boolean exists = authorRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Author with id: "+ id +" doesn't exist");
        }
        authorRepository.deleteById(id);
    }
}