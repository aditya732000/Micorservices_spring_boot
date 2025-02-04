package com.aditya7812.order.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.aditya7812.order.dto.OrderDTO;
import com.aditya7812.order.model.Order;
import com.aditya7812.order.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final InventoryClient inventoryClient;

    public OrderService(OrderRepository orderRepository, KafkaTemplate<String, String> kafkaTemplate, InventoryClient inventoryClient) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.inventoryClient = inventoryClient;
    }

    public Order createOrder(OrderDTO request) {
        // Call Inventory Service to check stock
        /*boolean stockAvailable = inventoryClient.checkStock(request.getProductId(), request.getQuantity());

        if (!stockAvailable) {
            throw new RuntimeException("Stock unavailable!");
        }*/

        Order order = new Order();
        order.setStatus("PENDING");

        Order orderOutput = orderRepository.save(order);

        // Publish event to Kafka
        kafkaTemplate.send("order.placed", orderOutput.getId());

        return order;
    }
}
