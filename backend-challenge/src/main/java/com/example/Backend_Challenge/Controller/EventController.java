package com.example.backend_challenge.controller;

import com.example.backend_challenge.dto.EventDTO;
import com.example.backend_challenge.entity.EventEntity;
import com.example.backend_challenge.service.EventService;
import com.example.backend_challenge.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("events")
@Slf4j
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @SuppressWarnings("unused")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Page<EventEntity>>> getEvents(@RequestParam(required = false)  String sortField,
                                                                    @RequestParam(required = false)  String sortDirection,
                                                                    @RequestParam(required = false)  Integer pageSize,
                                                                    @RequestParam(required = false)  Integer pageNumber,
                                                                    @RequestParam(required = false)String searchTerm){

        Page<EventEntity> result = eventService.getAllEvents(searchTerm,pageSize,pageNumber,sortField,sortDirection);
        ApiResponse<Page<EventEntity>> response = new ApiResponse<>("All events are retrieved successfully",result, HttpStatus.OK);
        log.info("All events are retrieved successfully");
        return  ResponseEntity.ok(response);
    }

    @SuppressWarnings("unused")
    @GetMapping("/{id}")
    public  ResponseEntity<ApiResponse<EventEntity>> getEventById(@PathVariable Long id){

        EventEntity result = eventService.getEventId(id);
        ApiResponse<EventEntity> response = new ApiResponse<>("Event with id "+id+" is retrieved successfully",result, HttpStatus.OK);
        log.info("Event with id "+id+" is retrieved successfully");
        return  ResponseEntity.ok(response);
    }
    @SuppressWarnings("unused")
    @PostMapping
    public  ResponseEntity<ApiResponse<EventEntity>> createEvent(@Valid @RequestBody EventDTO eventDTO) throws IOException {

        EventEntity result = eventService.createEvent(eventDTO);
        ApiResponse<EventEntity> response = new ApiResponse<>("Event is created successfully",result, HttpStatus.CREATED);
        log.info("Event is created successfully");
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @SuppressWarnings("unused")
    @PutMapping("/{id}")
    public  ResponseEntity<ApiResponse<EventEntity>> updateEventById(@PathVariable Long id,@RequestBody EventDTO eventDTO){

        EventEntity result = eventService.updateEvent(eventDTO,id);
        ApiResponse<EventEntity> response = new ApiResponse<>("Event with id "+id+" is updated successfully",result, HttpStatus.OK);
        log.info("Event with id "+id+" is updated successfully");
        return  ResponseEntity.ok(response);
    }
    @SuppressWarnings("unused")
    @DeleteMapping("/{id}")
    public  ResponseEntity<ApiResponse<EventEntity>> deleteLocationById(@PathVariable Long id){

        eventService.deleteEvent(id);
        ApiResponse<EventEntity> response = new ApiResponse<>("Event with id "+id+" is deleted successfully",null, HttpStatus.OK);
        log.info("Event with id "+id+" is deleted successfully");
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
