package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingInfoDto {
    private Long bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    @Min(value = 0, message = "The amount has to be positive!")
    private BigDecimal fineAmount;
}
