package com.example.backend_challenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "event")
@Setter
@Getter
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "The event name must not be blank ")
    @Column(nullable = false)
    private String eventName;

    @NotNull(message = "An event must have a start date")
    @Column(nullable = false)
    private LocalDateTime eventStartDate;

    @NotNull(message = "An event must have an end date")
    @Column(nullable = false)
    private LocalDateTime  eventEndDate;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","events"})
    private LocationEntity eventLocation;


    private String eventImage;

    @NotBlank
    private String eventDescription;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","events"})
    private OrganizerEntity organizer;

    @OneToMany(mappedBy = "event",fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","event"})
    private Set<TicketEntity> tickets;


    @PrePersist
    private void prePersist() {
        if (eventImage == null) {
            eventImage = "uploads/defaultEventImage.png";
        }
    }

}
