package com.aditya7812.order.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class OrderItem {
    private Long productId;
    private int quantity;
}