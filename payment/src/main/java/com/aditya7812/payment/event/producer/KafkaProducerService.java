package com.aditya7812.payment.event.producer;

import java.util.List;
import java.util.Map;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.aditya7812.payment.dto.DeductQuantityEvent;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, Map<String, String>> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Map<String, String>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendDeductQuantityEvent(Map<String, String> event) {
        kafkaTemplate.send("deduct-quantity", event);
        System.out.println("✅ Deduct Quantity Event Send ");
    }
}
