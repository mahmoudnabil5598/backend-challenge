package com.example.backend_challenge.service;

import com.example.backend_challenge.dto.LocationDTO;
import com.example.backend_challenge.dto.response.LocationResponseDTO;
import com.example.backend_challenge.entity.LocationEntity;
import org.springframework.data.domain.Page;

public interface LocationService {

    Page<LocationResponseDTO>getAllLocations(String searchString, Integer pageSize, Integer pageNumber, String sortField, String sortDirection);
    LocationEntity getLocationById(Long id);

    LocationEntity createLocation(LocationDTO location);

    LocationEntity updateLocation(LocationDTO location,Long id);

    void deleteLocation(Long id);


}
