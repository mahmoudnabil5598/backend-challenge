package com.example.backend_challenge.service;

import com.example.backend_challenge.dto.UserDTO;
import com.example.backend_challenge.entity.TicketEntity;
import com.example.backend_challenge.entity.UserEntity;

public interface UserService {

    UserEntity getUserById(long UserId);

    UserEntity createUser(UserDTO userDTO);
}
