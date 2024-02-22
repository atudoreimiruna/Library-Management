package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookPutDto {
    @NotNull
    private Long id;
    private String title;
    @Length(max = 13, message = "ISBN has max length 13!")
    private String isbn;
    private int quantity;
}
