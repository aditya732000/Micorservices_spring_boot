package com.aditya7812.payment.controller;

import com.aditya7812.payment.dto.DeductQuantityEvent;
import com.aditya7812.payment.event.producer.KafkaProducerService;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.LineItem;
import com.stripe.model.Product;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payment/webhook")
public class StripeWebhookController {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Value("${stripe.webhook.secret}")
    private String webhookSecret;

    @Autowired
    public KafkaProducerService kafkaProducer;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }

    @PostMapping
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload, 
                                                      @RequestHeader("Stripe-Signature") String sigHeader) {
        try {
            // Verify the webhook signature
            Event event = Webhook.constructEvent(payload, sigHeader, webhookSecret);
            System.out.println(event.getType());

            if ("checkout.session.completed".equals(event.getType())) {
                System.out.println("INside checkout");
                Session session = (Session) event.getDataObjectDeserializer().getObject().get();
                
                // Extract metadata from line items
                List<LineItem> lineItems = session.listLineItems().getData();
                //List<DeductQuantityEvent> deductQuantityEvents = new ArrayList<>();

                for (LineItem item : lineItems) {
                    String stripeProductId = item.getPrice().getProduct();
                    Product product = Product.retrieve(stripeProductId);

                    Map<String, String> metadata = product.getMetadata();
                    Map<String, String> eventItem = new HashMap<>();
                    eventItem.put("productId", metadata.get("productId"));
                    eventItem.put("quantity", metadata.get("quantity"));


                    /*DeductQuantityEvent eventItem = new DeductQuantityEvent();
                    eventItem.setProductId(metadata.get("productId"));
                    eventItem.setQuantity(metadata.get("quantity"));*/
                    kafkaProducer.sendDeductQuantityEvent(eventItem);
                    //deductQuantityEvents.add(eventItem);
                    
                    if (metadata.containsKey("orderId")) {
                        System.out.println("Order ID for product " + metadata.get("productId") + ": " + metadata.get("orderId"));
                    }

                }
                //kafkaProducer.sendDeductQuantityEvent(deductQuantityEvents);

            }

            return ResponseEntity.ok("Webhook received");
        } catch (SignatureVerificationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Webhook processing error");
        }
    }
}