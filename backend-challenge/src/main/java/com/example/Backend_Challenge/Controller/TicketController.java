package com.example.backend_challenge.controller;

import com.example.backend_challenge.dto.TicketDTO;
import com.example.backend_challenge.dto.response.TicketResponseDTO;
import com.example.backend_challenge.entity.TicketEntity;
import com.example.backend_challenge.service.TicketService;
import com.example.backend_challenge.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
@Slf4j
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @SuppressWarnings("unused")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Page<TicketResponseDTO>>> getTickets(@RequestParam(required = false)  String sortField,
                                                                      @RequestParam(required = false)  String sortDirection,
                                                                      @RequestParam(required = false)  Integer pageSize,
                                                                      @RequestParam(required = false)  Integer pageNumber,
                                                                      @RequestParam(required = false)String searchTerm){

        Page<TicketResponseDTO> result = ticketService.getAllTickets(searchTerm,pageSize,pageNumber,sortField,sortDirection);
        ApiResponse<Page<TicketResponseDTO>> response = new ApiResponse<>("All tickets are retrieved successfully",result, HttpStatus.OK);
        log.info("All tickets are retrieved successfully");
        return  ResponseEntity.ok(response);
    }

    @SuppressWarnings("unused")
    @GetMapping("/{id}")
    public  ResponseEntity<ApiResponse<TicketEntity>> getEventById(@PathVariable Long id){

        TicketEntity result = ticketService.getTicketById(id);
        ApiResponse<TicketEntity> response = new ApiResponse<>("Ticket with id "+id+" is retrieved successfully",result, HttpStatus.OK);
        log.info("Ticket with id "+id+" is retrieved successfully");
        return  ResponseEntity.ok(response);
    }
    @SuppressWarnings("unused")
    @PostMapping
    public  ResponseEntity<ApiResponse<TicketEntity>> createTicket(@Valid @RequestBody TicketDTO ticketDTO){

        TicketEntity result = ticketService.createTicket(ticketDTO);
        ApiResponse<TicketEntity> response = new ApiResponse<>("Ticket is created successfully",result, HttpStatus.CREATED);
        log.info("Ticket is created successfully");
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @SuppressWarnings("unused")
    @PutMapping("/{id}")
    public  ResponseEntity<ApiResponse<TicketEntity>> updateTicketById(@PathVariable Long id,@RequestBody TicketDTO ticketDTO){

        TicketEntity result = ticketService.updateTicket(ticketDTO,id);
        ApiResponse<TicketEntity> response = new ApiResponse<>("Ticket with id "+id+" is updated successfully",result, HttpStatus.OK);
        log.info("Ticket with id "+id+" is updated successfully");
        return  ResponseEntity.ok(response);
    }
    @SuppressWarnings("unused")
    @DeleteMapping("/{id}")
    public  ResponseEntity<ApiResponse<TicketEntity>> deleteTicketById(@PathVariable Long id){

        ticketService.deleteTicket(id);
        ApiResponse<TicketEntity> response = new ApiResponse<>("Ticket with id "+id+" is deleted successfully",null, HttpStatus.OK);
        log.info("Ticket with id "+id+" is deleted successfully");
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
