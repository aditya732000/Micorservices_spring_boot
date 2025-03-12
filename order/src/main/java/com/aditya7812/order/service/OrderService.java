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
            order.setName(item.getName());
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

    }

    public List<Order> getUserOrders(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
