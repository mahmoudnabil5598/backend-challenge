package com.example.backend_challenge.service;


import com.example.backend_challenge.dto.TicketDTO;
import com.example.backend_challenge.dto.response.TicketResponseDTO;
import com.example.backend_challenge.entity.TicketEntity;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface TicketService {

    Page<TicketResponseDTO> getAllTickets(String searchString, Integer pageSize, Integer pageNumber, String sortField, String sortDirection);
    TicketEntity getTicketById(Long id);

    TicketEntity createTicket(TicketDTO ticket);

    TicketEntity updateTicket(TicketDTO ticket,Long id);

    void deleteTicket(Long id);

    void reduceProductQuantity(int Quantity,Long productId);

    void addProductQuantity(int difference, Long ticketId);
}
