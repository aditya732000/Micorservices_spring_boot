package com.aditya7812.order.dto;

import java.util.List;

import com.aditya7812.order.model.OrderItem;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class OrderRequest {
    private String userId;
    private List<OrderItem> orderItems;
}