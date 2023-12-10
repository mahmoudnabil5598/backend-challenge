package com.example.backend_challenge.dto;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EventDTO {




    @NotBlank(message = "The event name must not be blank ")
    private String eventName;


    @NotNull(message = "An event must have a start date")
    @PastOrPresent(message = "Event start date must be in the past or present")
    private LocalDateTime eventStartDate;

    @NotNull(message = "An event must have an end date")
    @Future(message = "Event end date must be in the future")
    private LocalDateTime  eventEndDate;

    @NotNull(message = "An event must have location")
    private Long eventLocation;

    private MultipartFile eventImage;

    @NotBlank(message = "event must have a description")
    private String eventDescription;

    @NotNull(message = "An event must have organizer")
    private Long organizer;


    public EventDTO (String eventName,LocalDateTime eventStartDate,LocalDateTime  eventEndDate,Long eventLocation,String eventDescription, Long organizer){

        this.eventName=eventName;
        this.eventStartDate=eventStartDate;
        this.eventEndDate=eventEndDate;
        this.eventLocation=eventLocation;
        this.eventDescription=eventDescription;
        this.organizer=organizer;

    }

}
