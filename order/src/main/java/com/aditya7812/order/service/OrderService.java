package com.aditya7812.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aditya7812.order.dto.OrderRequest;
import com.aditya7812.order.model.Order;
import com.aditya7812.order.model.OrderItem;
import com.aditya7812.order.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> createOrder(String userId, OrderRequest request) {
        List<Order> createdOrders = new ArrayList<>();
        for (OrderItem item : request.getOrderItems()){

            Order order = new Order();
            order.setUserId(userId);
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
