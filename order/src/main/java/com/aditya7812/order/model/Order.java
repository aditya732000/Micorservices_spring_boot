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
    private String userId;

    @ElementCollection
    private List<OrderItem> orderItems;
    private String status;  // PENDING, PAID, SHIPPED, CANCELED
}