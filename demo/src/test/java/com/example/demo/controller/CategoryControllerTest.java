package com.example.demo.controller;

import com.example.demo.dto.CategoryDto;
import com.example.demo.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CategoryService categoryService;
    @BeforeEach
    public void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testAddCategory_Success() throws Exception {
        // Arrange
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Fiction");
        Long categoryId = 1L;
        when(categoryService.addCategory(any(CategoryDto.class))).thenReturn(categoryId);
        // Act and Assert
        mockMvc.perform(post("/api/category/addCategory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Fiction\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    public void testDeleteCategory_Success() throws Exception {
        // Arrange
        doNothing().when(categoryService).deleteCategoryById(anyLong());
        // Act and Assert
        mockMvc.perform(delete("/api/category/deleteCategory/{id}", 1L))
                .andExpect(status().isOk());
    }
}