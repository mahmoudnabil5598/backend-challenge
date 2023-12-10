package com.example.backend_challenge.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageStorageService {

    String saveImage(MultipartFile image) throws IOException;
}
