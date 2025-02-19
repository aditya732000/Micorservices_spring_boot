package com.aditya7812.user.dto;

import lombok.Data;


@Data
public class LoginRequest {
    String email;
    String password;
}