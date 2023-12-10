package com.example.backend_challenge.seeder;

import com.example.backend_challenge.dto.EventDTO;
import com.example.backend_challenge.repository.EventRepository;
import com.example.backend_challenge.service.EventService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@DependsOn({"organizerSeeder", "locationSeeder"})
public class EventSeeder {
    private final EventService eventService;

    private final EventRepository eventRepository;


    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    public EventSeeder(EventService eventService, EventRepository eventRepository, EntityManager entityManager) {
        this.eventService = eventService;
        this.eventRepository = eventRepository;
        this.entityManager = entityManager;

    }

    @SneakyThrows
    @PostConstruct
    @Transactional
    public void seedEvents() {
        if (eventRepository.count() == 0) {


            LocalDateTime today = LocalDateTime.now();
            LocalDateTime after5Days = today.plusDays(5);
            LocalDateTime before5Days = today.minusDays(5);
            LocalDateTime before10Days = today.minusDays(10);


            // create 3 events for Organizer 1
            EventDTO event1_1 = new EventDTO("event 1_1",today, after5Days,2L,"event description",1L);
            EventDTO event1_2  =  new EventDTO("event 1_2",today, after5Days,2L,"event description",1L);
            EventDTO event1_3  =  new EventDTO("event 1_3",before10Days,before5Days,3L,"event description",1L);

            // create 3 events for Organizer 2
            EventDTO event2_1 = new EventDTO("event2_1",today, after5Days,1L,"event description",2L);
            EventDTO event2_2  =  new EventDTO("event2_2",today, after5Days,2L,"event description",2L);
            EventDTO event2_3  =  new EventDTO("event2_3",before10Days,before5Days,3L,"event description",2L);


            // create 3 events for Organizer 3
            EventDTO event3_1 = new EventDTO("event3_1",today, after5Days,1L,"event description",3L);
            EventDTO event3_2  =  new EventDTO("event3_1",today, after5Days,2L,"event description",3L);
            EventDTO event3_3  =  new EventDTO("event3_1",before10Days,before5Days,3L,"event description",3L);


            // create 3 events for Organizer 4
            EventDTO event4_1 = new EventDTO("event4_1",today, after5Days,1L,"event description",4L);
            EventDTO event4_2  =  new EventDTO("event4_2",today, after5Days,2L,"event description",4L);
            EventDTO event4_3  =  new EventDTO("event4_3",before10Days,before5Days,3L,"event description",4L);


            // create 3 events for Organizer 5
            EventDTO event5_1 = new EventDTO("event5_1",today, after5Days,1L,"event description",5L);
            EventDTO event5_2  =  new EventDTO("event5_2",today, after5Days,2L,"event description",5L);
            EventDTO event5_3  =  new EventDTO("event5_3",before10Days,before5Days,3L,"event description",5L);






            // save lo to database
            eventService.createEvent(event1_1);
            eventService.createEvent(event1_2);
            eventService.createEvent(event1_3);

            eventService.createEvent(event2_1);
            eventService.createEvent(event2_2);
            eventService.createEvent(event2_3);

            eventService.createEvent(event3_1);
            eventService.createEvent(event3_2);
            eventService.createEvent(event3_3);

            eventService.createEvent(event4_1);
            eventService.createEvent(event4_2);
            eventService.createEvent(event4_3);

            eventService.createEvent(event5_1);
            eventService.createEvent(event5_2);
            eventService.createEvent(event5_3);




        }
    }
}
