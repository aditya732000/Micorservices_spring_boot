package com.aditya7812.order.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long productId;
    private int quantity;
    private int price; 

    private String fullName;
    private String address;
    private String city;
    private int zipCode;

    private String status;  // PENDING, PAID, SHIPPED, CANCELED
}