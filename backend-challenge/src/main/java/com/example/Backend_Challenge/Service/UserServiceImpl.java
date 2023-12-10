package com.example.backend_challenge.service;

import com.example.backend_challenge.dto.UserDTO;
import com.example.backend_challenge.entity.UserEntity;
import com.example.backend_challenge.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.backend_challenge.util.SystemUtil.unwrapEntity;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;


    private String entityName = "UserEntity";

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getUserById(long userId) {
        return unwrapEntity(userRepository.findById(userId),userId, entityName);
    }

    @Override
    public UserEntity createUser(UserDTO userDTO) {


        UserEntity user= new UserEntity();
        BeanUtils.copyProperties(userDTO,user);

        return userRepository.save(user) ;
    }
}
