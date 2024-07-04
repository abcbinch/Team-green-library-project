package com.library.service.admin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class StorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${file.upload-dir2}")
    private String uploadDir2;


    public String store(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        Path uploadPath = Paths.get(uploadDir);
        Path uploadPath2 = Paths.get(uploadDir2);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        if (!Files.exists(uploadPath2)) {
            Files.createDirectories(uploadPath2);
        }

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);
        System.out.println(filePath);
        Path filePath2 = uploadPath2.resolve(fileName);
        Files.copy(file.getInputStream(), filePath2);
        System.out.println(filePath2);
        return fileName;
    }

}
