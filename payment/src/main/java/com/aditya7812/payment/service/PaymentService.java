package com.aditya7812.payment.service;

import com.aditya7812.payment.dto.OrderItem;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentService {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    public Map<String, String> createCheckoutSession(List<OrderItem> dto) throws StripeException {
        Stripe.apiKey = stripeApiKey;

        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
        for (int i = 0; i < dto.size(); i++) {
            Map<String, String> metadata = new HashMap<>();
            metadata.put("productId", dto.get(i).getProductId());
            metadata.put("orderId", dto.get(i).getOrderId());
            metadata.put("quantity", String.valueOf(dto.get(i).getQuantity()));
            lineItems.add(
                SessionCreateParams.LineItem.builder()
                    .setQuantity(dto.get(i).getQuantity())
                    .setPriceData(
                        SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency("inr")
                            .setUnitAmount(dto.get(i).getPrice()) // Price in cents
                            .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(dto.get(i).getProductId())
                                .putAllMetadata(metadata)
                                .build())
                            .build())
                    .build()
            );
        }

        SessionCreateParams params = SessionCreateParams.builder()
            .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
            .setMode(SessionCreateParams.Mode.PAYMENT)
            .setSuccessUrl("https://5174-aditya7812-ecommercemic-5oxq92yh6rp.ws-us118.gitpod.io/success") // Change based on frontend URL
            .setCancelUrl("https://5174-aditya7812-ecommercemic-5oxq92yh6rp.ws-us118.gitpod.io/cancel")
            .addAllLineItem(lineItems)
            .build();

        Session session = Session.create(params);
        Map<String, String> response = new HashMap<>();
        response.put("id", session.getId());
        return response;
    }
}
