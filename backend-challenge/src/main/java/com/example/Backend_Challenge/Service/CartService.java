package com.example.backend_challenge.service;

import com.example.backend_challenge.dto.CartDTO;
import com.example.backend_challenge.entity.CartEntity;
import com.example.backend_challenge.enums.CartStatus;

import java.util.List;

public interface CartService {

    CartEntity addToCart(Long userId, List<CartDTO>cartItems);

    CartEntity viewCart(Long userId);

    List<CartEntity> getCartsByStatus(CartStatus cartStatus);

    void deleteCart(Long id);

    void updateCart(Long id, CartEntity cart);
}
