package com.aditya7812.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    
    private String shopName;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    private Long phoneNumber;
    
    private String location;
    
    private String zipCode;

    // Getters and Setters
}
