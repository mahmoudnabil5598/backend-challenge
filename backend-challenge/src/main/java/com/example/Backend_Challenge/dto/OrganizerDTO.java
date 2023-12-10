package com.example.backend_challenge.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@AllArgsConstructor
public class OrganizerDTO {
    @NotBlank(message = "An organizer must have a name")
    private String organizerName;

    @NotBlank(message = "An organizer must have a description")
    private String organizerDescription;

    private MultipartFile organizerImage;

    public OrganizerDTO(String organizerName, String organizerDescription) {

        this.organizerName=organizerName;
        this.organizerDescription=organizerDescription;
    }
}
