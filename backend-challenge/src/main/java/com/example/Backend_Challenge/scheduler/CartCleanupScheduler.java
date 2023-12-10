package com.example.backend_challenge.scheduler;

import com.example.backend_challenge.entity.CartEntity;
import com.example.backend_challenge.entity.OrderItemEntity;
import com.example.backend_challenge.enums.CartStatus;
import com.example.backend_challenge.service.CartService;
import com.example.backend_challenge.service.OrderItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CartCleanupScheduler {

    private final CartService cartService;

    private final OrderItemService orderItemService;
@Autowired
    public CartCleanupScheduler(CartService cartService,OrderItemService orderItemService) {
        this.cartService = cartService;
        this.orderItemService=orderItemService;
    }

    @Scheduled(fixedDelay = 20 * 60 * 1000)
    @Transactional// Run every 20 minutes
    public void cleanupOldOrderItems() {
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("Clean Up running");
        List<CartEntity> cartsInProgress = cartService.getCartsByStatus(CartStatus.IN_PROGRESS);

        for (CartEntity cart : cartsInProgress) {
            Set<OrderItemEntity> orderItems = cart.getOrderItems();

            // Filter order items that are 20 minutes behind
            List<OrderItemEntity> outdatedOrderItems = orderItems.stream()
                    .filter(item -> item.getUpdatedAt().plusMinutes(20).isBefore(currentTime))
                    .collect(Collectors.toList());

            for (OrderItemEntity outdatedOrderItem : outdatedOrderItems) {
                // Remove outdated order items from the cart
                orderItems.remove(outdatedOrderItem);
                orderItemService.deleteOrderItem(outdatedOrderItem.getId());

            }
            cart.setOrderItems(orderItems);

            if (orderItems.isEmpty()) {
                // If the cart has no order items, delete it
                cartService.deleteCart(cart.getId());
            } else {
                // Update the cart with the modified order items
                cartService.updateCart(cart.getId(), cart);
            }
        }
    }
}

