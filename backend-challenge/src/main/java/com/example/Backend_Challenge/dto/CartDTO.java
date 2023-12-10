package com.example.backend_challenge.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CartDTO {
    @NotNull(message = "There must be a product")
    private Long ticket;
    @PositiveOrZero(message = "Quantity must be equal 0 or more")
    private Integer quantity;
}
