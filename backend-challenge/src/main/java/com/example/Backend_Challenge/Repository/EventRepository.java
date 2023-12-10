package com.example.backend_challenge.repository;

import com.example.backend_challenge.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface EventRepository extends JpaRepository<EventEntity,Long> {
    @Query("SELECT E FROM EventEntity E " +
            "WHERE (LOWER(E.eventName) LIKE %:searchString% " +
            "OR LOWER(E.eventLocation.locationName) LIKE %:searchString% " +
            "OR LOWER(E.eventDescription) LIKE %:searchString% " +
            "OR LOWER(E.organizer.organizerName) LIKE %:searchString%) " +
            "AND CURRENT_TIMESTAMP BETWEEN E.eventStartDate AND E.eventEndDate")
    Page<EventEntity> findAll(String searchString, Pageable pageable);
;
}
