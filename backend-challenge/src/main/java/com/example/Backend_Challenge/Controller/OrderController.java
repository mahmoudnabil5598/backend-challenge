package com.example.backend_challenge.controller;

import com.example.backend_challenge.dto.OrderDTO;
import com.example.backend_challenge.entity.OrderEntity;
import com.example.backend_challenge.exception.PromoCodeExpiredException;
import com.example.backend_challenge.exception.PromoCodeUsageLimitExceededException;
import com.example.backend_challenge.service.OrderService;
import com.example.backend_challenge.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("orders")
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @SuppressWarnings("unused")
    @GetMapping("/{userId}")
    public  ResponseEntity<ApiResponse<Set<OrderEntity>>> getUserByUserId(@PathVariable Long userId){

        Set<OrderEntity> result = orderService.getOrderHistory(userId);
        ApiResponse<Set<OrderEntity>> response = new ApiResponse<>("History for user with id "+userId+" is retrieved successfully",result, HttpStatus.OK);
        log.info("History for user with id "+userId+" is retrieved successfully");
        return  ResponseEntity.ok(response);
    }
    @SuppressWarnings("unused")
    @PostMapping("/checkout")
    public  ResponseEntity<ApiResponse<OrderEntity>> checkout(@Valid @RequestBody OrderDTO orderDTO) throws PromoCodeExpiredException, PromoCodeUsageLimitExceededException {

        OrderEntity result = orderService.CheckOut(orderDTO);
        ApiResponse<OrderEntity> response = new ApiResponse<>("Order is created successfully",result, HttpStatus.CREATED);
        log.info("Order is created successfully");
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @SuppressWarnings("unused")
    @PutMapping("/refund/{id}")
    public  ResponseEntity<ApiResponse<OrderEntity>> refundOrder(@PathVariable Long id){

        OrderEntity result = orderService.refundOrder(id);
        ApiResponse<OrderEntity> response = new ApiResponse<>("Order with id "+id+" is updated successfully",result, HttpStatus.OK);
        log.info("Order with id "+id+" is updated successfully");
        return  ResponseEntity.ok(response);
    }

}
