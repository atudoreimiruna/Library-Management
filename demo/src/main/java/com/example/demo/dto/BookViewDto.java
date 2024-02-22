package com.example.demo.dto;

import com.example.demo.enums.BorrowingStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookViewDto {
    private Long bookId;
    private String title;
    private String isbn;
    @NotNull
    private BorrowingStatusEnum status;
}