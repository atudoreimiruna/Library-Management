package com.example.demo.model;

import com.example.demo.enums.BorrowingStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Length(max = 13)
    private String isbn;
    private int quantity;
    private BorrowingStatusEnum status;

    @OneToMany(mappedBy = "book")
    private List<Borrowing> borrowing;

    @OneToMany(mappedBy = "book")
    private List<BookAuthor> bookAuthor;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
