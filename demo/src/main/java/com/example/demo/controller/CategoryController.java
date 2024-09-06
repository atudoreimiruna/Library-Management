package com.example.demo.controller;

import com.example.demo.dto.CategoryDto;
import com.example.demo.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "CategoryController")
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/addCategory")
    @Operation(summary = "add category", description = "POST method for adding a new category of books")
    public ResponseEntity<Long> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Long categoryId = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(categoryId, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCategory/{id}")
    @Operation(summary = "delete category", description = "DELETE method for removing a category of books")
    public ResponseEntity<?> delete(@PathVariable Long id){ categoryService.deleteCategoryById(id); return new ResponseEntity<>(HttpStatus.OK); }
}