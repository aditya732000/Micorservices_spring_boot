package com.aditya7812.order.model;

import java.time.LocalDate;

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

    private String name;

    private int quantity;

    private int price; 

    private String fullName;

    private String address;

    private String city;

    private int zipCode;

    @Temporal(TemporalType.DATE)
    private LocalDate createdAt;

    private String status;  // PENDING, PAID, SHIPPED, CANCELED
}