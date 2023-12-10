package com.example.backend_challenge.entity;

import com.example.backend_challenge.exception.RefundException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","tickets"})
    private EventEntity event;


    @Column(nullable = false)
    private String ticketType;

    @Column(nullable = false)
    private int initialLimit;

    @Column(nullable = false)
    private int ticketLimit;

    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private int daysToRefund;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","ticket"})
    private Set<OrderItemEntity> orderItems;

    @PrePersist
    @PreUpdate
    private void validateDates() {
        if (event != null && event.getEventStartDate() != null && event.getEventEndDate() != null) {
            LocalDateTime refundDeadline = event.getEventStartDate().plusDays(daysToRefund);

            if (refundDeadline.isAfter(event.getEventEndDate())) {
                throw new RefundException("Order  is not eligible for a refund.");
            }
        }
    }


}
