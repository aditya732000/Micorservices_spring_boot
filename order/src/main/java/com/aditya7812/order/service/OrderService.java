package com.aditya7812.order.service;

import java.util.ArrayList;
import java.util.*;
import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.aditya7812.order.dto.OrderRequest;
import com.aditya7812.order.model.Order;
import com.aditya7812.order.model.OrderItem;
import com.aditya7812.order.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderService(OrderRepository orderRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public List<Order> createOrder(String userId, OrderRequest request) {
        List<Order> createdOrders = new ArrayList<>();
        for (OrderItem item : request.getOrderItems()){

            Order order = new Order();
            order.setUserId(Long.parseLong(userId));
            order.setProductId(item.getProductId());
            order.setPrice(item.getPrice());
            order.setQuantity(item.getQuantity());
            order.setFullName(request.getShippingInfo().getFullName());
            order.setAddress(request.getShippingInfo().getAddress());
            order.setCity(request.getShippingInfo().getCity());
            order.setZipCode(request.getShippingInfo().getZipCode());
            order.setStatus("PENDING");

            order = orderRepository.save(order);

            createdOrders.add(order);
        }

        return createdOrders;

        // Call Inventory Service to check stock
        /*boolean stockAvailable = inventoryClient.checkStock(request.getProductId(), request.getQuantity());

        if (!stockAvailable) {
            throw new RuntimeException("Stock unavailable!");
        }

        Order order = new Order();
        order.setStatus("PENDING");

        Order orderOutput = orderRepository.save(order);

        // Publish event to Kafka
        kafkaTemplate.send("order.placed", orderOutput.getId());

        return order;*/
    }
}
