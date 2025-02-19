package com.aditya7812.cart.event.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.aditya7812.cart.repository.CartItemRepository;

@Service
public class CartEventListener {

    private final CartItemRepository cartRepository;

    public CartEventListener(CartItemRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @KafkaListener(topics = "payment.success", groupId = "cart-group")
    public void handlePaymentSuccess(String userId) {
        System.out.println("✅ Payment successful for User ID: " + userId + ". Clearing cart...");

        // Clear the cart for the user
        cartRepository.deleteByUserId(userId);
        
        System.out.println("🛒 Cart cleared for User ID: " + userId);
    }
}