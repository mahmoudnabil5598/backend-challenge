package com.example.backend_challenge.controller;

import com.example.backend_challenge.dto.TicketDTO;
import com.example.backend_challenge.dto.UserDTO;
import com.example.backend_challenge.entity.TicketEntity;
import com.example.backend_challenge.entity.UserEntity;
import com.example.backend_challenge.service.TicketService;
import com.example.backend_challenge.service.UserService;
import com.example.backend_challenge.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserEntity>> createUser(@Valid @RequestBody UserDTO userDTO){
        System.out.println(userDTO);

        UserEntity result = userService.createUser(userDTO);
        ApiResponse<UserEntity> response = new ApiResponse<>("User is created successfully",result, HttpStatus.CREATED);
        log.info("User is created successfully");
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
