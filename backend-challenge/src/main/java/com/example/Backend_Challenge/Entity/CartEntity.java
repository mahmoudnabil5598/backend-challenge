package com.example.backend_challenge.entity;

import com.example.backend_challenge.enums.CartStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cart")
@Setter
@Getter
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","cart"})
    private UserEntity user;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "cart")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","cart"})
    private Set<OrderItemEntity> orderItems = new HashSet<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private CartStatus status;
    @Transient
    private int TotalPrice;

    public int getTotalPrice(){
        int price=0;
        for(OrderItemEntity orderItem:this.orderItems)
                price+=orderItem.getTicket().getPrice();
        return price;
    }
}
