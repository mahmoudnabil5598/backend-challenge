package com.example.backend_challenge.service;

import com.example.backend_challenge.dto.CartDTO;
import com.example.backend_challenge.entity.*;
import com.example.backend_challenge.enums.CartStatus;
import com.example.backend_challenge.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.backend_challenge.util.SystemUtil.copyNonNullProperties;
import static com.example.backend_challenge.util.SystemUtil.unwrapEntity;

@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;

    private final UserService userService;

    private final TicketService ticketService;
    private final OrderItemService orderItemService;

    private String entityName="cartEntity";
    @Autowired
    public  CartServiceImpl(CartRepository cartRepository,UserService userService,TicketService ticketService,OrderItemService orderItemService){
        this.cartRepository=cartRepository;
        this.userService=userService;
        this.ticketService=ticketService;
        this.orderItemService=orderItemService;
    }
    @Override
    @Transactional
    public CartEntity addToCart(Long userId, List<CartDTO> cartItems) {
        UserEntity user = userService.getUserById(userId);
        CartEntity cart = cartRepository.findByUser(user);

        if (cart == null) {
            System.out.println("Hena");
            cart = new CartEntity();
            cart.setUser(user);
            cart=cartRepository.save(cart);
        }

        Set<OrderItemEntity> orderItems = cart.getOrderItems(); // Get existing order items

        for (CartDTO cartItem : cartItems) {
            Long ticketId = cartItem.getTicket();
            int quantity = cartItem.getQuantity();


            Optional<OrderItemEntity> existingOrderItem = orderItems.stream()
                    .filter(item -> item.getTicket().getId()==(ticketId))
                    .findFirst();

            if (existingOrderItem.isPresent()) {
                // If the ticket is already in the cart
                OrderItemEntity orderItem = existingOrderItem.get();
                int oldQuantity = orderItem.getQuantity();

                if (oldQuantity < quantity) {
                    // If the old quantity is smaller than the current quantity, add the difference
                    int difference = quantity - oldQuantity;
                    orderItem.setQuantity(quantity);
                    orderItem.setTotalPrice(quantity*ticketService.getTicketById(ticketId).getPrice());
                    // Update the product quantity
                    ticketService.reduceProductQuantity(difference, ticketId);
                } else if (oldQuantity > quantity) {
                    // If the old quantity is greater than the current quantity, reduce the difference
                    int difference = oldQuantity - quantity;
                    orderItem.setQuantity(quantity);
                    orderItem.setTotalPrice(quantity*ticketService.getTicketById(ticketId).getPrice());
                    // Update the product quantity
                    ticketService.addProductQuantity(difference, ticketId);
                }
            } else {
                // If the ticket is not in the cart, add a new OrderItemEntity
                ticketService.reduceProductQuantity(quantity, ticketId);

                OrderItemEntity orderItem = new OrderItemEntity();
                orderItem.setTicket(ticketService.getTicketById(ticketId));
                orderItem.setQuantity(quantity);
                orderItem.setTotalPrice(quantity*ticketService.getTicketById(ticketId).getPrice());
                orderItem.setCart(cart);
                orderItems.add(orderItem);
            }
        }

        cart.setOrderItems(orderItems);
        cart.setStatus(CartStatus.IN_PROGRESS);
        for(OrderItemEntity orderItem:orderItems){
            orderItem.setCart(cart);
            orderItemService.createOrderItem(orderItem);
        }
        System.out.println(cart.getUser());
        return cartRepository.save(cart);


    }

    @Override
    public CartEntity viewCart(Long userId) {
        return cartRepository.findByUser(userService.getUserById(userId));
    }

    @Override
    public List<CartEntity> getCartsByStatus(CartStatus cartStatus) {
        return cartRepository.findByStatus(cartStatus);
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void updateCart(Long id, CartEntity cart) {
        CartEntity oldCart = unwrapEntity(cartRepository.findById(id), id, entityName);



        copyNonNullProperties(cart, oldCart);

       cartRepository.save(oldCart);
    }
}
