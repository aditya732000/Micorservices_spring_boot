package com.aditya7812.user.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    String name;
    String email;
    String password;
    String role;
    String shopName;
    Long phoneNumber;
    String location;
    String zipCode;
}