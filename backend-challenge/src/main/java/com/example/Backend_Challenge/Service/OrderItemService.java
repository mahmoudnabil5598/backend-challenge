package com.example.backend_challenge.service;

import com.example.backend_challenge.entity.OrderItemEntity;

public interface OrderItemService {

    OrderItemEntity createOrderItem(OrderItemEntity orderItem);

    void deleteOrderItem(Long id);
    void updateOrderItem(OrderItemEntity orderItem);
}
