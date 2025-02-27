package com.aditya7812.order.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aditya7812.order.dto.OrderRequest;
import com.aditya7812.order.model.Order;
import com.aditya7812.order.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<List<Order>> createOrder(@RequestHeader String userId, @RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(userId, request));
    }
}