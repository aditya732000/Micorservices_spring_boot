package com.aditya7812.products_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aditya7812.products_service.dto.AddQuantityDTO;
import com.aditya7812.products_service.dto.ProductDTO;
import com.aditya7812.products_service.model.Product;
import com.aditya7812.products_service.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/create")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productService.createProduct(dto));
    }

    @PostMapping("/add-quantity")
    public ResponseEntity<Product> addQuantity(@RequestBody AddQuantityDTO dto) {
        return ResponseEntity.ok(productService.addQuantity(dto));
    }
    

    @GetMapping("/hello")
    public ResponseEntity<String> checkout() {
        System.out.print("cjidscji");
        return ResponseEntity.ok("Hii");
    }
}