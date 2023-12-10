package com.example.backend_challenge.repository;

import com.example.backend_challenge.dto.response.TicketResponseDTO;
import com.example.backend_challenge.entity.TicketEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepository extends JpaRepository<TicketEntity,Long> {

    @Query("SELECT T.id as id ," +
            "T.event.eventName as eventName , " +
            "T.event.eventStartDate  as eventStartDate ," +
            "T.event.eventEndDate as eventEndDate , " +
            "T.event.eventLocation.locationName as location ," +
            "T.event.eventImage as image ," +
            "T.event.organizer.organizerName as organizer , " +
            "T.ticketType as ticketType , " +
            "T.price as ticketPrice , " +
            "T.daysToRefund as daysToRefund " +
            " FROM TicketEntity T " +
            "WHERE (LOWER(T.event.eventName) LIKE %:searchString% " +
            "OR LOWER(T.ticketType) LIKE %:searchString% )")
    Page<TicketResponseDTO> findAll(String searchString, Pageable pageable);
}
