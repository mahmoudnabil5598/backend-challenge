package com.example.backend_challenge.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {

    private String message;
    private T data;
    private HttpStatus status;

}