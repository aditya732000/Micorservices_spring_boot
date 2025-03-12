package com.aditya7812.products_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductDTO {
    
    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than 0")
    private BigDecimal price;

    @Min(value = 0, message = "Stock must be non-negative")
    private int quantity;

    @NotBlank(message = "Category is required")
    private String category;

    private MultipartFile image;

}

