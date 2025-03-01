package com.aditya7812.products_service.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.aditya7812.products_service.service.ProductService;

import java.util.Map;

@Service
public class ProductEventConsumer {

    @Autowired
    public ProductService productService;

    @KafkaListener(topics = "deduct-quantity", groupId = "product-service")
    public void consumeDeductQuantityEvent(Map<String, String> event) {
        System.out.println("📩 Received Deduct Quantity Event ");

        productService.deductQuantity(event.get("productId"), Integer.parseInt(event.get("quantity")));
        
    }
}
