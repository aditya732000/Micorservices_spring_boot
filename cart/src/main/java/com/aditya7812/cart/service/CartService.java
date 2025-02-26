package com.aditya7812.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aditya7812.cart.dto.CartItemDTO;
import com.aditya7812.cart.model.CartItem;
import com.aditya7812.cart.repository.CartItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItem addToCart(CartItemDTO dto, String userId) {
        Optional<CartItem> existingCartItem = cartItemRepository.findByUserIdAndProductId(userId, dto.getProductId());

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + dto.getQuantity());
            return cartItemRepository.save(cartItem);
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setUserId(userId);
            newCartItem.setProductId(dto.getProductId());
            newCartItem.setQuantity(dto.getQuantity());
            newCartItem.setPrice(dto.getPrice());
            return cartItemRepository.save(newCartItem);
        }
    }

    public List<CartItemDTO> getCartItems(String userId) {
        return cartItemRepository.findByUserId(userId).stream().map(cartItem -> {
            CartItemDTO dto = new CartItemDTO();
            dto.setProductId(cartItem.getProductId());
            dto.setQuantity(cartItem.getQuantity());
            dto.setPrice(cartItem.getPrice());
            return dto;
        }).collect(Collectors.toList());
    }

    public void removeCartItem(String id) {
        cartItemRepository.deleteById(id);
    }

    public void clearCart(String userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}
