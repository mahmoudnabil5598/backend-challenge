package com.example.backend_challenge.service;


import com.example.backend_challenge.dto.OrganizerDTO;
import com.example.backend_challenge.entity.OrganizerEntity;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface OrganizerService {

    Page<OrganizerEntity> getAllOrganizers(String searchString, Integer pageSize, Integer pageNumber, String sortField, String sortDirection);
    OrganizerEntity getOrganizerById(Long id);

    OrganizerEntity createOrganizer(OrganizerDTO organizer) throws IOException;

    OrganizerEntity updateOrganizer(OrganizerDTO organizer,Long id);

    void deleteOrganizer(Long id);
}
