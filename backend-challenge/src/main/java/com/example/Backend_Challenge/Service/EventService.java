package com.example.backend_challenge.service;

import com.example.backend_challenge.dto.EventDTO;
import com.example.backend_challenge.entity.EventEntity;
import com.example.backend_challenge.entity.LocationEntity;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface EventService {
    Page<EventEntity> getAllEvents(String searchString, Integer pageSize, Integer pageNumber, String sortField, String sortDirection);
    EventEntity getEventId(Long id);

    EventEntity createEvent(EventDTO event) throws IOException;

    EventEntity updateEvent(EventDTO event,Long id);

    void deleteEvent(Long id);
}
