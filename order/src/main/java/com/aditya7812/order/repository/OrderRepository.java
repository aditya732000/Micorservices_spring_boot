package com.aditya7812.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aditya7812.order.model.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(String userId);
}
