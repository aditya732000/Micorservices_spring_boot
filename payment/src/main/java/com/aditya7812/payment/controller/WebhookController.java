package com.aditya7812.payment.controller;

import com.aditya7812.payment.model.Payment;
import com.aditya7812.payment.repository.PaymentRepository;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/payment/webhook")
public class WebhookController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Value("${stripe.webhook.secret}")
    private String endpointSecret;

    @PostMapping
    public ResponseEntity<String> handleWebhook(@RequestBody String payload,
                                                @RequestHeader("Stripe-Signature") String sigHeader) {

        Event event;
        try {
            // Verify the webhook signature using Stripe's library
            event = Webhook.constructEvent(
                payload, sigHeader, endpointSecret
            );

            if ("payment_intent.succeeded".equals(event.getType())) {
                // Payment succeeded event
                PaymentIntent paymentIntent = (PaymentIntent) event.getDataObjectDeserializer().getObject().get();

                String transactionId = paymentIntent.getId();
                Optional<Payment> paymentOpt = paymentRepository.findById(transactionId);

                paymentOpt.ifPresent(payment -> {
                    payment.setPaymentStatus("SUCCESS");
                    paymentRepository.save(payment);
                });

                System.out.println("✅ Payment successful: " + transactionId);
            }

            return ResponseEntity.ok("Event handled");

        } catch (Exception e) {
            System.out.println("⚠️ Webhook error: " + e.getMessage());
            return ResponseEntity.badRequest().body("Webhook error");
        }
    }
}