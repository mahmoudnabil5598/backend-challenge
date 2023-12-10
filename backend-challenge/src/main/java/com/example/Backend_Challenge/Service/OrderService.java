package com.example.backend_challenge.service;


import com.example.backend_challenge.dto.OrderDTO;
import com.example.backend_challenge.entity.OrderEntity;
import com.example.backend_challenge.exception.PromoCodeExpiredException;
import com.example.backend_challenge.exception.PromoCodeUsageLimitExceededException;

import java.util.Set;

public interface OrderService {

    OrderEntity CheckOut(OrderDTO orderDTO) throws PromoCodeExpiredException, PromoCodeUsageLimitExceededException;

    Set<OrderEntity> getOrderHistory(Long UserId);

    OrderEntity refundOrder(Long OrderId);

}
