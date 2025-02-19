package com.aditya7812.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aditya7812.user.dto.LoginRequest;
import com.aditya7812.user.dto.RegisterRequest;
import com.aditya7812.user.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest dto) {
        System.out.println(dto);
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.login(dto));
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"error\": \"Invalid credentials\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody RegisterRequest dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.register(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Error registering user\"}");
        }
    }
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.status(200).body("Hello");
    }
}