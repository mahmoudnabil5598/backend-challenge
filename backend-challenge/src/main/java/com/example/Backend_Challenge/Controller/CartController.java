package com.example.backend_challenge.controller;

import com.example.backend_challenge.dto.CartDTO;
import com.example.backend_challenge.dto.EventDTO;
import com.example.backend_challenge.entity.CartEntity;
import com.example.backend_challenge.entity.EventEntity;
import com.example.backend_challenge.service.CartService;
import com.example.backend_challenge.service.EventService;
import com.example.backend_challenge.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("cart")
@Slf4j
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add/items/{userId}")
    public ResponseEntity<ApiResponse<CartEntity>> createCart(@Valid @RequestBody List<CartDTO> cart, @PathVariable Long userId) throws IOException {

        CartEntity result = cartService.addToCart(userId,cart);
        ApiResponse<CartEntity> response = new ApiResponse<>("Cart is created successfully",result, HttpStatus.CREATED);
        log.info("Cart is created successfully");
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<CartEntity>> getCart(@PathVariable Long userId) throws IOException {

        CartEntity result = cartService.viewCart(userId);
        ApiResponse<CartEntity> response = new ApiResponse<>("Cart is retrieved successfully",result, HttpStatus.CREATED);
        log.info("Cart is retrieved successfully");
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
