package com.example.backend_challenge.entity;

import com.example.backend_challenge.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "purchaseOrders")
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @Min(value = 1,message = "Total price minimum value is 1")
    @Column(nullable = false)
    private int totalPrice;

    @Min(value = 1,message = "Total quantity minimum value is 1")
    @Column(nullable = false)
    private int totalQuantity;


    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private UserEntity userIdentity;


    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "purchaseOrder",fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","purchaseOrder"})
    private Set<OrderItemEntity>orderItems;

    private OrderStatus orderStatus;

    @PrePersist
    private void prePersist() {
        if (orderStatus == null) {
            orderStatus = OrderStatus.COMPLETED;
        }
    }
}
