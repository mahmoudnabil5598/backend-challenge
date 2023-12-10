package com.example.backend_challenge.repository;

import com.example.backend_challenge.entity.OrganizerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrganizerRepository  extends JpaRepository<OrganizerEntity,Long> {
    @Query("SELECT O FROM OrganizerEntity O where Lower(O.organizerName) like %:searchString% OR Lower(O.organizerDescription) like %:searchString%")
    Page<OrganizerEntity> findAll(String searchString, Pageable pageable);
}
