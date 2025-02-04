package com.aditya7812.payment.event.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentEvent(String orderId) {
        kafkaTemplate.send("payment.success", orderId);
        System.out.println("✅ Payment event sent to Kafka for Order ID: " + orderId);
    }
}
