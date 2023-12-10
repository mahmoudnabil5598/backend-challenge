package com.example.backend_challenge.controller;

import com.example.backend_challenge.dto.LocationDTO;
import com.example.backend_challenge.dto.response.LocationResponseDTO;
import com.example.backend_challenge.entity.LocationEntity;
import com.example.backend_challenge.service.LocationService;
import com.example.backend_challenge.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("locations")
@Slf4j
@SuppressWarnings("unused")
public class LocationController {


    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
    @SuppressWarnings("unused")
    @GetMapping("/all")
    public  ResponseEntity<ApiResponse<Page<LocationResponseDTO>>> getLocations(@RequestParam(required = false)  String sortField,
                                                                           @RequestParam(required = false)  String sortDirection,
                                                                           @RequestParam(required = false)  Integer pageSize,
                                                                           @RequestParam(required = false)  Integer pageNumber,
                                                                           @RequestParam(required = false)String searchTerm){

        Page<LocationResponseDTO> result = locationService.getAllLocations(searchTerm,pageSize,pageNumber,sortField,sortDirection);
        ApiResponse<Page<LocationResponseDTO>> response = new ApiResponse<>("All locations are retrieved successfully",result, HttpStatus.OK);
        log.info("All locations are retrieved successfully");
        return  ResponseEntity.ok(response);
    }

    @SuppressWarnings("unused")
    @GetMapping("/{id}")
    public  ResponseEntity<ApiResponse<LocationEntity>> getLocationById(@PathVariable Long id){

        LocationEntity result = locationService.getLocationById(id);
        ApiResponse<LocationEntity> response = new ApiResponse<>("Location with id "+id+" is retrieved successfully",result, HttpStatus.OK);
        log.info("Location with id "+id+" is retrieved successfully");
        return  ResponseEntity.ok(response);
    }
    @SuppressWarnings("unused")
    @PostMapping
    public  ResponseEntity<ApiResponse<LocationEntity>> createLocation(@Valid  @RequestBody LocationDTO locationDTO){

        LocationEntity result = locationService.createLocation(locationDTO);
        ApiResponse<LocationEntity> response = new ApiResponse<>("Location is created successfully",result, HttpStatus.CREATED);
        log.info("Location is created successfully");
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @SuppressWarnings("unused")
    @PutMapping("/{id}")
    public  ResponseEntity<ApiResponse<LocationEntity>> updateLocationById(@PathVariable Long id,@RequestBody LocationDTO locationDTO){

        LocationEntity result = locationService.updateLocation(locationDTO,id);
        ApiResponse<LocationEntity> response = new ApiResponse<>("Location with id "+id+" is updated successfully",result, HttpStatus.OK);
        log.info("Location with id "+id+" is updated successfully");
        return  ResponseEntity.ok(response);
    }
    @SuppressWarnings("unused")
    @DeleteMapping("/{id}")
    public  ResponseEntity<ApiResponse<LocationEntity>> deleteLocationById(@PathVariable Long id){

         locationService.deleteLocation(id);
        ApiResponse<LocationEntity> response = new ApiResponse<>("Location with id "+id+" is deleted successfully",null, HttpStatus.OK);
        log.info("Location with id "+id+" is deleted successfully");
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}