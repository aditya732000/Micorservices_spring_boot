package com.aditya7812.payment.service;

import com.aditya7812.payment.dto.PaymentDTO;
import com.aditya7812.payment.model.Payment;
import com.aditya7812.payment.repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    public String processPayment(PaymentDTO dto) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        // Create a PaymentIntent in Stripe
        Map<String, Object> params = new HashMap<>();
        params.put("amount", dto.getAmount().multiply(new java.math.BigDecimal(100)).longValue()); // Convert to cents
        params.put("currency", dto.getCurrency());
        params.put("payment_method_types", java.util.Collections.singletonList("card"));

        PaymentIntent intent = PaymentIntent.create(params);

        // Save payment details in the database
        Payment payment = new Payment();
        payment.setUserId(dto.getUserId());
        payment.setOrderId(dto.getOrderId());
        payment.setAmount(dto.getAmount());
        payment.setCurrency(dto.getCurrency());
        payment.setPaymentStatus("PENDING");
        payment.setTransactionId(intent.getId());
        paymentRepository.save(payment);

        return intent.getClientSecret();
    }

    public Optional<Payment> getPaymentByOrderId(String orderId) {
        return paymentRepository.findByOrderId(orderId);
    }
}