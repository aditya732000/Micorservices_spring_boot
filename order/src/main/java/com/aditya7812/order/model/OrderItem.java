package com.aditya7812.order.model;

import lombok.Data;

@Data
public class OrderItem {
    private String productId;
    private int quantity;
    private int price;
    private Long sellerId;
}