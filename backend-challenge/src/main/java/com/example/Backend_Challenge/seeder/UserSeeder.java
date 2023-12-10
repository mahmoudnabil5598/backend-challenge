package com.example.backend_challenge.seeder;

import com.example.backend_challenge.dto.LocationDTO;
import com.example.backend_challenge.dto.UserDTO;
import com.example.backend_challenge.repository.UserRepository;
import com.example.backend_challenge.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder {
    private final UserService userService;

    private final UserRepository userRepository;


    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    public UserSeeder(UserService userService, UserRepository userRepository, EntityManager entityManager) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.entityManager = entityManager;

    }

    @PostConstruct
    @Transactional
    public void seedLocations() {
        if (userRepository.count() == 0) {

            // create 5 users
            UserDTO user1 = new UserDTO("user1");
            UserDTO user2=  new UserDTO("user2");
            UserDTO user3 = new UserDTO("user3");
            UserDTO user4 =  new UserDTO("user4");
            UserDTO user5 =  new UserDTO("user5");


            // save lo to database
            userService.createUser(user1);
            userService.createUser(user2);
            userService.createUser(user3);
            userService.createUser(user4);
            userService.createUser(user5);

        }
    }

}
