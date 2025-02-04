package com.aditya7812.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aditya7812.order.model.Order;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderId(String orderId);
}
