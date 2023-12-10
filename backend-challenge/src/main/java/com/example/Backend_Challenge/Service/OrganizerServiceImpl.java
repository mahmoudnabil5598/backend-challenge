package com.example.backend_challenge.service;

import com.example.backend_challenge.dto.OrganizerDTO;
import com.example.backend_challenge.entity.OrganizerEntity;
import com.example.backend_challenge.repository.OrganizerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.example.backend_challenge.util.SystemUtil.*;

@Service
public class OrganizerServiceImpl implements OrganizerService {

    private final OrganizerRepository organizerRepository;
    private final ImageStorageService imageStorageService;

    private String entityName="OrganizerEntity";

    @Autowired
    public OrganizerServiceImpl(OrganizerRepository organizerRepository, ImageStorageService imageStorageService) {
        this.organizerRepository = organizerRepository;
        this.imageStorageService = imageStorageService;
    }
    @Override
    public Page<OrganizerEntity> getAllOrganizers(String searchString, Integer pageSize, Integer pageNumber, String sortField, String sortDirection) {
        Pageable paginate= pagination(sortField,sortDirection,pageNumber,pageSize);
        searchString= searchString==null?"":searchString.toLowerCase();
        return  organizerRepository.findAll(searchString,paginate);
    }

    @Override
    public OrganizerEntity getOrganizerById(Long id) {
        return  unwrapEntity(organizerRepository.findById(id),id,entityName);
    }

    @Override
    public OrganizerEntity createOrganizer(OrganizerDTO organizerDTO) throws IOException {
        OrganizerEntity organizer = new OrganizerEntity();
        BeanUtils.copyProperties(organizerDTO,organizer);

        if ((organizerDTO.getOrganizerImage() != null && !organizerDTO.getOrganizerImage().isEmpty())) {
            String imagePath = imageStorageService.saveImage(organizerDTO.getOrganizerImage());
            organizer.setOrganizerImage(imagePath);
        }

        return organizerRepository.save(organizer);
    }

    @Override
    public OrganizerEntity updateOrganizer(OrganizerDTO organizer, Long id) {
        OrganizerEntity oldOrganizer = unwrapEntity(organizerRepository.findById(id), id, entityName);
        copyNonNullProperties(organizer, oldOrganizer);
        return organizerRepository.save(oldOrganizer);
    }

    @Override
    public void deleteOrganizer(Long id) {
        organizerRepository.deleteById(id);
    }
}
