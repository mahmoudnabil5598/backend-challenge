package com.example.backend_challenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "orderItem")
@Getter
@Setter
public class OrderItemEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "An Item must have minimum quantity of 1")
    int quantity;

    @Min(value = 0)
    int totalPrice;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","orderItems"})
    OrderEntity purchaseOrder;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","orderItems"})
    TicketEntity ticket;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    CartEntity cart;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
