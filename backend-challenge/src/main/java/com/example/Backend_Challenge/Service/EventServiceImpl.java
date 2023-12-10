package com.example.backend_challenge.service;

import com.example.backend_challenge.dto.EventDTO;
import com.example.backend_challenge.entity.EventEntity;
import com.example.backend_challenge.entity.LocationEntity;
import com.example.backend_challenge.entity.OrganizerEntity;
import com.example.backend_challenge.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

import static com.example.backend_challenge.util.SystemUtil.*;

@Service
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;

    private  final LocationService locationService;

    private final ImageStorageService imageStorageService;

    private final OrganizerService organizerService;


    @Autowired
    public EventServiceImpl(EventRepository eventRepository,LocationService locationService,OrganizerService organizerService,ImageStorageService imageStorageService) {
        this.eventRepository = eventRepository;
        this.locationService=locationService;
        this.organizerService=organizerService;
        this.imageStorageService=imageStorageService;
    }



    private String entityName="eventEntity";
    @Override
    public Page<EventEntity> getAllEvents(String searchString, Integer pageSize, Integer pageNumber, String sortField, String sortDirection) {

        Pageable paginate= pagination(sortField,sortDirection,pageNumber,pageSize);
        searchString= searchString==null?"":searchString.toLowerCase();
        return eventRepository.findAll(searchString,paginate);
    }

    @Override
    public EventEntity getEventId(Long id) {
        return  unwrapEntity(eventRepository.findById(id),id,entityName);
    }

    @Override
    @Transactional
    public EventEntity createEvent(EventDTO event) throws IOException {

        //Get the Location by id
        LocationEntity location = locationService.getLocationById(event.getEventLocation());

        // Get the Organizer by id
        OrganizerEntity organizer = organizerService.getOrganizerById(event.getOrganizer());


        EventEntity eventObject= new EventEntity();
        BeanUtils.copyProperties(event,eventObject);

        eventObject.setEventLocation(location);
        eventObject.setOrganizer(organizer);
        if ((event.getEventImage() != null && !event.getEventImage().isEmpty())) {
            String imagePath = imageStorageService.saveImage(event.getEventImage());
            organizer.setOrganizerImage(imagePath);
        }

        return eventRepository.save(eventObject);



    }

    @Override
    public EventEntity updateEvent(EventDTO event, Long id) {



        EventEntity oldEvent = unwrapEntity(eventRepository.findById(id), id, entityName);

        //Check if the Location is Updated
        if(event.getEventLocation()!=null) {
            LocationEntity location = locationService.getLocationById(event.getEventLocation());
            oldEvent.setEventLocation(location);
        }

        //Check if the Organizer is Updated
        if(event.getOrganizer()!=null) {
            OrganizerEntity organizer = organizerService.getOrganizerById(event.getOrganizer());
            oldEvent.setOrganizer(organizer);
        }


        copyNonNullProperties(event, oldEvent);

        return eventRepository.save(oldEvent);



    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
