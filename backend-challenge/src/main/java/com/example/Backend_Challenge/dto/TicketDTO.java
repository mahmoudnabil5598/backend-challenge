package com.example.backend_challenge.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketDTO {


    @NotNull(message = "An event must have location")
    private Long event;
    @NotBlank(message = "a ticket must have a ticket type")
    private String ticketType;

    @Min(value = 1,message = "A price must have minimum limit of 1")
    private int ticketLimit;

    @Min(value = 0,message = "A price must have minimum value of 0")
    private int price;

    @Min(value = 0,message = "A ticket must have minimum 0 days of refund")
    private int daysToRefund;


    public TicketDTO (Long event, String ticketType, int  ticketLimit, int price, int daysToRefund){

        this.event=event;
        this.ticketType=ticketType;
        this.ticketLimit=ticketLimit;
        this.price=price;
        this.daysToRefund=daysToRefund;

    }
}
