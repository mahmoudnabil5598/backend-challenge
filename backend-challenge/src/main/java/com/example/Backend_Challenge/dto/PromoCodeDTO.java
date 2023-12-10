package com.example.backend_challenge.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
public class PromoCodeDTO {


    @NotNull(message = "A promoCode must have an end date")
    @PastOrPresent(message = "PromoCode start date must be in the past or present")
    private LocalDateTime startDate;

    @NotNull(message = "A promoCode must have an end date")
    @Future(message = "promoCode end date must be in the future")
    private LocalDateTime endDate;

    @Min(value = 1)
    @Max(value = 100)
    private int discountPercentage;

    @Min(value = 1)
    private Integer usageLimit;

    @NotNull(message = "A promo code must have a code")
    private String code;


    private Set<Long> events;

}
