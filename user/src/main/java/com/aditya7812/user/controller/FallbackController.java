package com.aditya7812.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping
    public ResponseEntity<String> fallback() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Requested service is unavailable\"}");
    }
}
