package com.aditya7812.payment.controller;

import com.aditya7812.payment.dto.OrderItem;
import com.aditya7812.payment.service.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<Map<String, String>> processPayment(@RequestBody List<OrderItem> dto) {
        try {
            return ResponseEntity.ok(paymentService.createCheckoutSession(dto));
        } catch (StripeException e) {
            System.out.println(e);
            
            return ResponseEntity.badRequest().build();
        }
    }

}