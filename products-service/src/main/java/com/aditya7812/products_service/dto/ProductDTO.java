package com.aditya7812.products_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.math.BigDecimal;

@Data
public class ProductDTO {
    
    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be greater than 0")
    private BigDecimal price;

    @Min(value = 0, message = "Stock must be non-negative")
    private int stockQuantity;

    @NotBlank(message = "Category is required")
    private String category;

    private String imageUrl;
}
