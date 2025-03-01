package com.aditya7812.payment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    public String productId;
    public String orderId;
    public Long price;
    public Long quantity;
    
}
