package com.example.backend_challenge.repository;

import com.example.backend_challenge.dto.response.LocationResponseDTO;
import com.example.backend_challenge.entity.LocationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocationRepository extends JpaRepository<LocationEntity,Long> {


 @Query("SELECT L.id as id,L.locationName as locationName,L.locationOnMap as locationOnMap FROM LocationEntity L where Lower(L.locationName) like %:searchString%")
    Page<LocationResponseDTO>findAll(String searchString, Pageable pageable);
}
