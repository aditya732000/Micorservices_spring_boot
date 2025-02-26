package com.aditya7812.cart.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

import com.aditya7812.cart.dto.CartItemDTO;
import com.aditya7812.cart.model.CartItem;
import com.aditya7812.cart.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(@Valid @RequestBody CartItemDTO dto, HttpServletRequest request) {
        String userId = request.getHeader("userId");
        return ResponseEntity.ok(cartService.addToCart(dto, userId));
    }
    @GetMapping("/get")
    public ResponseEntity<List<CartItemDTO>> getCartItems(HttpServletRequest request) {
        String userId = request.getHeader("userId");
        return ResponseEntity.ok(cartService.getCartItems(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCartItem(@PathVariable String id) {
        cartService.removeCartItem(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(HttpServletRequest request) {
        String userId = request.getHeader("userId");
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
