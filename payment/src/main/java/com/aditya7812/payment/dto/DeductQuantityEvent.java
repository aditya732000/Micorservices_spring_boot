package com.aditya7812.payment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeductQuantityEvent {
    public String productId;
    public String quantity;
    
}
