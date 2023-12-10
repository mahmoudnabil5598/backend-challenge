package com.example.backend_challenge.seeder;

import com.example.backend_challenge.dto.LocationDTO;
import com.example.backend_challenge.repository.LocationRepository;
import com.example.backend_challenge.service.LocationService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationSeeder {


    private final LocationService locationService;

    private final LocationRepository locationRepository;


    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    public LocationSeeder(LocationService locationService, LocationRepository locationRepository, EntityManager entityManager) {
        this.locationService = locationService;
        this.locationRepository = locationRepository;
        this.entityManager = entityManager;

    }

    @PostConstruct
    @Transactional
    public void seedLocations() {
        if (locationRepository.count() == 0) {

            // create 5 Locations
            LocationDTO location1 = new LocationDTO("Location 1","Location 1 on Map");
            LocationDTO location2 =  new LocationDTO("Location 2","Location 2 on Map");
            LocationDTO location3 =  new LocationDTO("Location 3","Location 3 on Map");
            LocationDTO location4 =  new LocationDTO("Location 4","Location 4 on Map");
            LocationDTO location5 =  new LocationDTO("Location 5","Location 5 on Map");


            // save lo to database
            locationService.createLocation(location1);
            locationService.createLocation(location2);
            locationService.createLocation(location3);
            locationService.createLocation(location4);
            locationService.createLocation(location5);

        }
    }

}
