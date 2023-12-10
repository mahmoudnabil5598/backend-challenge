package com.example.backend_challenge.repository;

import com.example.backend_challenge.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
