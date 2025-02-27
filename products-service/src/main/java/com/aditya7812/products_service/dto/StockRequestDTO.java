package com.aditya7812.products_service.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockRequestDTO {
    List<StockProduct> products;
}


