package com.example.backend_challenge.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {



    @NotBlank(message = "userName is required")
    @Length(min = 3,message = "username must be minimum of 3 characters")
    private String userName;

}
