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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String userId;
    private String productId;
    private int quantity;
    private int price; 

    private String fullName;
    private String address;
    private String city;
    private int zipCode;

    private String status;  // PENDING, PAID, SHIPPED, CANCELED
}