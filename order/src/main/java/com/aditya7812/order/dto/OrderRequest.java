package com.aditya7812.order.dto;

import java.util.List;

import com.aditya7812.order.model.OrderItem;
import com.aditya7812.order.model.ShippingInfo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class OrderRequest {
    private List<OrderItem> orderItems;
    private ShippingInfo shippingInfo;
}