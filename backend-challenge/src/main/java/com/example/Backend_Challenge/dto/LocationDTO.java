package com.example.backend_challenge.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationDTO {

    @NotBlank(message = "location must have a name")
    private String locationName;

    @NotBlank(message = "a location must have a location on map")
    private String locationOnMap;
}
