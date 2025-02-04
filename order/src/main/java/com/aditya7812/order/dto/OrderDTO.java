package com.aditya7812.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    private Double amount;
    private Long productId;
    private Double quantity;
    
}
