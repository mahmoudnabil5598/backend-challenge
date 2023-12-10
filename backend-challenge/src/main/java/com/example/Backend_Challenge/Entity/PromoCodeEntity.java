package com.example.backend_challenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "promoCode")
@Data
public class PromoCodeEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private int discountPercentage;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private Integer usageLimit;

    @Column(nullable = false)
    private int initialLimit;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(name = "promoCode",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "event",referencedColumnName = "id")
    )
    private Set<EventEntity> events= new HashSet<>();

    @PrePersist
    @PreUpdate
    private void prePersist() {
        if (isActive == null) {
            isActive = true;
        }
    }
}
