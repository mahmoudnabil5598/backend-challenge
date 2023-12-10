package com.example.backend_challenge.service;


import com.example.backend_challenge.dto.OrderDTO;
import com.example.backend_challenge.entity.*;
import com.example.backend_challenge.enums.CartStatus;
import com.example.backend_challenge.enums.OrderStatus;
import com.example.backend_challenge.exception.PromoCodeExpiredException;
import com.example.backend_challenge.exception.PromoCodeUsageLimitExceededException;
import com.example.backend_challenge.exception.RefundException;
import com.example.backend_challenge.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

import static com.example.backend_challenge.util.SystemUtil.unwrapEntity;

@Service
public class OrderServiceImpl implements OrderService{

    private final CartService cartService;

    private final UserService userService;

    private  final  PromoCodeService promoCodeService;

    private final OrderRepository orderRepository;

    private final OrderItemService orderItemService;
    private  final  TicketService ticketService;

    @Autowired
    public  OrderServiceImpl(CartService cartService,UserService userService,PromoCodeService promoCodeService,OrderRepository orderRepository,OrderItemService orderItemService,TicketService ticketService){
        this.cartService=cartService;
        this.userService=userService;
        this.promoCodeService=promoCodeService;
        this.orderRepository=orderRepository;
        this.orderItemService=orderItemService;
        this.ticketService=ticketService;
    }
    @Override
    @Transactional
    public OrderEntity CheckOut(OrderDTO orderDTO) throws PromoCodeExpiredException, PromoCodeUsageLimitExceededException {

        //Get The User Cart
        Long userId = orderDTO.getUserId();

        // Let The Cart Status equal CheckOut
        CartEntity cart =cartService.viewCart(userId);
        cart.setStatus(CartStatus.CHECKOUT);
        cartService.updateCart(cart.getId(), cart);

        //Get List of OrderItems
        Set<OrderItemEntity> orderItems = cart.getOrderItems();

        //Create new Order
        OrderEntity order = new OrderEntity();

        //Populate data
        order.setUserIdentity(userService.getUserById(userId));
        order.setOrderItems(orderItems);

        order.setTotalQuantity(orderItems.stream()
                .mapToInt(OrderItemEntity::getQuantity)
                .sum());


        //Promo code Logic
        String promoCode = orderDTO.getPromoCode();
        if(promoCode!=null) {
            PromoCodeEntity promo = promoCodeService.getPromoCodeByCode(promoCode);

            double discountPercentage = (double) promo.getDiscountPercentage() / 100;
            if (LocalDateTime.now().isAfter(promo.getEndDate()))
                throw new PromoCodeExpiredException("Promo code has expired.");
            if (promo.getUsageLimit() == 0)
                throw new PromoCodeUsageLimitExceededException("Promo code usage limit exceeded.");
            for(OrderItemEntity orderItem :orderItems) {
                for (EventEntity event : promo.getEvents()) {
                    if (event.equals(orderItem.getTicket().getEvent())) {
                        orderItem.setTotalPrice((int) (orderItem.getTotalPrice()-(orderItem.getTotalPrice() * discountPercentage)));
                        orderItemService.updateOrderItem(orderItem);
                    }
                }
            }
        }
        order.setTotalPrice(orderItems.stream()
                .mapToInt(OrderItemEntity::getTotalPrice)
                .sum());
        OrderEntity orderObject = orderRepository.save(order);

        for(OrderItemEntity orderItem :orderItems) {
            orderItem.setPurchaseOrder(orderObject);
            orderItem.setCart(null);
            orderItemService.updateOrderItem(orderItem);
        }

        //Delete The Cart
        cartService.deleteCart(cart.getId());
        return orderObject;
    }

    @Override
    public Set<OrderEntity> getOrderHistory(Long UserId) {
        return orderRepository.findByUserIdentity(userService.getUserById(UserId));
    }

    @Override
    @Transactional
    public OrderEntity refundOrder(Long OrderId) {

       OrderEntity order= unwrapEntity(orderRepository.findById(OrderId),OrderId,"OrderEntity");
       System.out.println(order.getOrderStatus());

        if (order.getOrderStatus() == OrderStatus.COMPLETED) {
            // Check if all order items meet the refund conditions
            boolean allItemsEligibleForRefund = order.getOrderItems().stream()
                    .allMatch(this::isOrderItemEligibleForRefund);

            if (allItemsEligibleForRefund) {
                System.out.println(true);
                // Perform the refund logic

                order.getOrderItems().forEach(orderItem -> {
                    // Add the quantity of tickets to the ticket when refunding
                    TicketEntity ticket = orderItem.getTicket();
                    ticketService.addProductQuantity(orderItem.getQuantity(),orderItem.getTicket().getId());
                });
                order.setOrderStatus(OrderStatus.REFUNDED);
                return orderRepository.save(order);
            }
        }

        // If the order doesn't meet the refund conditions, throw an exception or handle as needed
        throw new RefundException("Order is not eligible for a refund.");
    }


    private boolean isOrderItemEligibleForRefund(OrderItemEntity orderItem) {
        // Check if the ticket's days to refund + event start date is before or equal to the current date
        return  orderItem.getTicket().getEvent().getEventStartDate().plusDays(orderItem.getTicket().getDaysToRefund())
                .isBefore(LocalDateTime.now());
    }

}
