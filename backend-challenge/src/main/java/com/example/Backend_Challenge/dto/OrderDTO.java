package com.example.backend_challenge.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderDTO {
@NotNull(message = "must provide userId")
    Long userId;

    String promoCode;
}
