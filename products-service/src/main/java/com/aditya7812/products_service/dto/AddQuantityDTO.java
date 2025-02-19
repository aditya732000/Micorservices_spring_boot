package com.aditya7812.products_service.dto;

import lombok.Data;

@Data
public class AddQuantityDTO {
    public String productId;
    public Integer quantity;
    
}
