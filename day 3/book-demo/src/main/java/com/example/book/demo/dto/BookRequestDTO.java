package com.example.book.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 2, max = 100, message = "Title length must be between 2 and 100 characters")
    private String title;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be at least 1.0")
    private Double price;

    @NotNull(message = "Author ID is required")
    private Long authorId;
}