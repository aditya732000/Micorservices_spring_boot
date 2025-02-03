package com.aditya7812.cart.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aditya7812.cart.dto.CartItemDTO;
import com.aditya7812.cart.model.CartItem;
import com.aditya7812.cart.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<CartItem> addToCart(@Valid @RequestBody CartItemDTO dto) {
        return ResponseEntity.ok(cartService.addToCart(dto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItemDTO>> getCartItems(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.getCartItems(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCartItem(@PathVariable String id) {
        cartService.removeCartItem(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
