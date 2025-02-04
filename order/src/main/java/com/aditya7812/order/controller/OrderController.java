package com.aditya7812.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aditya7812.order.dto.OrderDTO;
import com.aditya7812.order.model.Order;
import com.aditya7812.order.repository.OrderRepository;
import com.aditya7812.order.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<Order> checkout(@RequestBody OrderDTO request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }
}