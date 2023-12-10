package com.example.backend_challenge.seeder;


import com.example.backend_challenge.dto.OrganizerDTO;
import com.example.backend_challenge.repository.OrganizerRepository;
import com.example.backend_challenge.service.OrganizerService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizerSeeder {

    private final OrganizerService organizerService;

    private final OrganizerRepository organizerRepository;


    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public OrganizerSeeder(OrganizerService organizerService, OrganizerRepository organizerRepository, EntityManager entityManager) {
        this.organizerService = organizerService;
        this.organizerRepository = organizerRepository;
        this.entityManager = entityManager;

    }

    @SneakyThrows
    @PostConstruct
    @Transactional
    public void seedOrganizers() {
        if (organizerRepository.count() == 0) {

            // create 5 Organizers
            OrganizerDTO organizer1 = new OrganizerDTO("Organizer 1","Organizer  1 description");
            OrganizerDTO organizer2 =  new OrganizerDTO("Organizer 2","Organizer  2 description");
            OrganizerDTO organizer3 =  new OrganizerDTO("Organizer 3","Organizer  3 description");
            OrganizerDTO organizer4 =  new OrganizerDTO("Organizer 4","Organizer  4 description");
            OrganizerDTO organizer5 =  new OrganizerDTO("Organizer 5","Organizer  5 description");


            // save lo to database
            organizerService.createOrganizer(organizer1);
            organizerService.createOrganizer(organizer2);
            organizerService.createOrganizer(organizer3);
            organizerService.createOrganizer(organizer4);
            organizerService.createOrganizer(organizer5);

        }
    }

}
