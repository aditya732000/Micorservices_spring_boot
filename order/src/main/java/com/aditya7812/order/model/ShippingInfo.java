package com.aditya7812.order.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingInfo {
    private String fullName;
    private String address;
    private String city;
    private int zipCode;
    
}
