package com.example.backend_challenge.repository;

import com.example.backend_challenge.entity.CartEntity;
import com.example.backend_challenge.entity.UserEntity;
import com.example.backend_challenge.enums.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity,Long> {

    CartEntity findByUser(UserEntity user);

    List<CartEntity> findByStatus(CartStatus cartStatus);
}
