package com.example.demo.dto;

import com.example.demo.enums.BorrowingStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    @NotNull
    private String title;
    @NotNull
    @Length(max = 13, message = "ISBN has max length 13!")
    private String isbn;
    private int quantity;
    @NotNull
    private String authorName;
    private LocalDate dateOfWriting;
    @NotNull
    private BorrowingStatusEnum status;
}
