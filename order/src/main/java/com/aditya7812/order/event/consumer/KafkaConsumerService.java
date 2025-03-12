package com.aditya7812.order.event.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.aditya7812.order.model.Order;
import com.aditya7812.order.repository.OrderRepository;

import java.util.Optional;

@Service
public class KafkaConsumerService {

    private final OrderRepository orderRepository;

    public KafkaConsumerService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @KafkaListener(topics = "payment.success", groupId = "order-group")
    public void handlePaymentSuccess(String orderId) {
        System.out.println("✅ Payment successful for Order ID: " + orderId);

        Optional<Order> orderOpt = orderRepository.findById(Long.parseLong(orderId));
        orderOpt.ifPresent(order -> {
            order.setStatus("PAID");
            orderRepository.save(order);
            System.out.println("✅ Order " + orderId + " status updated to PAID");
        });
    }
}
