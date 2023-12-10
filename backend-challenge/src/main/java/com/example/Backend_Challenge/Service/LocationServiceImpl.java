package com.example.backend_challenge.service;

import com.example.backend_challenge.dto.LocationDTO;
import com.example.backend_challenge.dto.response.LocationResponseDTO;
import com.example.backend_challenge.entity.LocationEntity;
import com.example.backend_challenge.repository.LocationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.backend_challenge.util.SystemUtil.*;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;


    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }



    private String entityName="locationEntity";
    @Override
    public Page<LocationResponseDTO> getAllLocations(String searchString, Integer pageSize, Integer pageNumber, String sortField, String sortDirection) {
        Pageable paginate= pagination(sortField,sortDirection,pageNumber,pageSize);
        searchString= searchString==null?"":searchString.toLowerCase();
        return  locationRepository.findAll(searchString,paginate);
    }

    @Override
    public LocationEntity getLocationById(Long id) {
        return  unwrapEntity(locationRepository.findById(id),id,entityName);
    }

    @Override
    public LocationEntity createLocation(LocationDTO location) {

        LocationEntity locationObject= new LocationEntity();
        BeanUtils.copyProperties(location, locationObject);
        return locationRepository.save(locationObject);
    }

    @Override
    public LocationEntity updateLocation(LocationDTO location, Long id) {

        LocationEntity oldLocation = unwrapEntity(locationRepository.findById(id), id, entityName);
        copyNonNullProperties(location, oldLocation);
        return locationRepository.save(oldLocation);

    }

    @Override
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

}
