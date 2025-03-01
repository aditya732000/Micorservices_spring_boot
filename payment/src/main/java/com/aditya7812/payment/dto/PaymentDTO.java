package com.aditya7812.payment.dto;

import lombok.Data;
import java.util.List;

@Data
public class PaymentDTO {
    List<OrderItem> orderItems;
}