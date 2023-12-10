package com.example.backend_challenge.dto.response;

import java.time.LocalDateTime;

public interface TicketResponseDTO {
    Long getId();
    String getEventName();
    LocalDateTime getEventStartDate();
    LocalDateTime getEventEndDate();
    String getLocation();
    String getImage();
    String getOrganizer();
    String getTicketType();
    int getTicketPrice();
    int getDaysToRefund();
}
