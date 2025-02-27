package com.aditya7812.products_service.controller;

import java.util.List;
import java.util.Optional;

import org.apache.hc.core5.reactor.SocksProxyProtocolHandlerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aditya7812.products_service.dto.AddQuantityDTO;
import com.aditya7812.products_service.dto.ProductDTO;
import com.aditya7812.products_service.dto.StockRequestDTO;
import com.aditya7812.products_service.model.Product;
import com.aditya7812.products_service.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestHeader String userId) {
        System.out.println(userId);
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/seller")
    public ResponseEntity<List<Product>> getSellerProducts(HttpServletRequest request) {
        String sellerId = request.getHeader("userId");
        return ResponseEntity.ok(productService.getSellerProducts(sellerId));
    }

    @PostMapping("/create")
    public ResponseEntity<Product> addProduct(HttpServletRequest request, @RequestBody ProductDTO dto) {
        String userId = request.getHeader("userId");

        return ResponseEntity.ok(productService.createProduct(dto, userId));
    }

    

    @PostMapping("/add-quantity")
    public ResponseEntity<Product> addQuantity(@RequestBody AddQuantityDTO dto) {
        return ResponseEntity.ok(productService.addQuantity(dto));
    }
    

    @GetMapping("/{productId}")
    public ResponseEntity<Optional<Product>> productDetails(@PathVariable String productId) {
        System.out.println("ProductID" + productId);
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @PostMapping("/check-stock")
    public Boolean checkStock(@RequestBody StockRequestDTO stockRequest) {
        return productService.checkStockAvailability(stockRequest);
        // Simulated check (In real case, fetch stock details from DB)
        /*for (Map.Entry<String, Integer> entry : stockRequest.entrySet()) {
            Long productId = Long.parseLong(entry.getKey());
            int quantity = entry.getValue();
            
            if (quantity > getAvailableStock(productId)) {
                return false;
            }
        }
        return true;*/
    }
}