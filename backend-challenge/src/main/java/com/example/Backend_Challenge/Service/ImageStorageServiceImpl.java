package com.example.backend_challenge.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
@Service
public class ImageStorageServiceImpl implements ImageStorageService{

    @Value("${uploads}")
    private  String uploadDir ;
    @Override
    public String saveImage(MultipartFile image) throws IOException {
                String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
                Path filePath = Path.of(uploadDir, fileName);

                Files.createDirectories(filePath.getParent());
                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                return filePath.toString();
            }
}
