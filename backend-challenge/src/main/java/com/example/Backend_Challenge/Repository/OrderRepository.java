package com.example.backend_challenge.repository;

import com.example.backend_challenge.entity.OrderEntity;
import com.example.backend_challenge.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

   Set<OrderEntity> findByUserIdentity(UserEntity user);
}
