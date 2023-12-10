package com.example.backend_challenge.service;

import com.example.backend_challenge.entity.OrderItemEntity;
import com.example.backend_challenge.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService{

    private final OrderItemRepository orderItemRepository;


    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository){
        this.orderItemRepository=orderItemRepository;
    }
    @Override
    public OrderItemEntity createOrderItem(OrderItemEntity orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
    @Override
    public void updateOrderItem(OrderItemEntity orderItem){
        orderItemRepository.save(orderItem);

    }
}
