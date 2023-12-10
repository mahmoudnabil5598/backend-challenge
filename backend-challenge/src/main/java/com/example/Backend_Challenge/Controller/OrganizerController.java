package com.example.backend_challenge.controller;

import com.example.backend_challenge.dto.LocationDTO;
import com.example.backend_challenge.dto.OrganizerDTO;
import com.example.backend_challenge.entity.LocationEntity;
import com.example.backend_challenge.entity.OrganizerEntity;
import com.example.backend_challenge.service.OrganizerService;
import com.example.backend_challenge.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("organizers")
@Slf4j
public class OrganizerController {

    private final OrganizerService organizerService;

    @Autowired
    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }
    @GetMapping("/all")
    public  ResponseEntity<ApiResponse<Page<OrganizerEntity>>> getOrganizers(@RequestParam(required = false)  String sortField,
                                                                           @RequestParam(required = false)  String sortDirection,
                                                                           @RequestParam(required = false)  Integer pageSize,
                                                                           @RequestParam(required = false)  Integer pageNumber,
                                                                           @RequestParam(required = false)String searchTerm){

        Page<OrganizerEntity> result = organizerService.getAllOrganizers(searchTerm,pageSize,pageNumber,sortField,sortDirection);
        ApiResponse<Page<OrganizerEntity>> response = new ApiResponse<>("All organizers are retrieved successfully",result, HttpStatus.OK);
        log.info("All organizers are retrieved successfully");
        return  ResponseEntity.ok(response);
    }

    @SuppressWarnings("unused")
    @GetMapping("/{id}")
    public  ResponseEntity<ApiResponse<OrganizerEntity>> getOrganizerById(@PathVariable Long id){

        OrganizerEntity result = organizerService.getOrganizerById(id);
        ApiResponse<OrganizerEntity> response = new ApiResponse<>("Organizer with id "+id+" is retrieved successfully",result, HttpStatus.OK);
        log.info("Organizer with id "+id+" is retrieved successfully");
        return  ResponseEntity.ok(response);
    }
    @SuppressWarnings("unused")
    @PostMapping
    public ResponseEntity<ApiResponse<OrganizerEntity>>  createOrganizer(
            @Valid @RequestBody OrganizerDTO organizerDTO
    ) throws IOException {

        OrganizerEntity result =organizerService.createOrganizer(organizerDTO);
        ApiResponse<OrganizerEntity> response = new ApiResponse<>("Organizer is created successfully",result, HttpStatus.CREATED);
        log.info("Organizer is created successfully");
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @PutMapping("/{id}")
    public  ResponseEntity<ApiResponse<OrganizerEntity>> updateOrganizerById(@PathVariable Long id, @RequestBody OrganizerDTO organizerDTO){

        OrganizerEntity result = organizerService.updateOrganizer(organizerDTO,id);
        ApiResponse<OrganizerEntity> response = new ApiResponse<>("Organizer with id "+id+" is updated successfully",result, HttpStatus.OK);
        log.info("Organizer with id "+id+" is updated successfully");
        return  ResponseEntity.ok(response);
    }

    @SuppressWarnings("unused")
    @DeleteMapping("/{id}")
    public  ResponseEntity<ApiResponse<LocationEntity>> deleteOrganizerById(@PathVariable Long id){

        organizerService.deleteOrganizer(id);
        ApiResponse<LocationEntity> response = new ApiResponse<>("Organizer with id "+id+" is deleted successfully",null, HttpStatus.OK);
        log.info("Organizer with id "+id+" is deleted successfully");
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}

