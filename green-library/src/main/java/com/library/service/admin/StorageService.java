package com.library.service.admin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Transactional
public class StorageService {

    //파일 위치
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${file.upload-dir2}")
    private String uploadDir2;

//    파일 생성
    public String store(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String encodedFilename = URLEncoder.encode(originalFilename, "UTF-8");
        String fileName = UUID.randomUUID().toString() + "-" + encodedFilename;

        Path uploadPath = Paths.get(uploadDir);
        Path uploadPath2 = Paths.get(uploadDir2);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        if (!Files.exists(uploadPath2)) {
            Files.createDirectories(uploadPath2);
        }
        byte[] fileContent = file.getBytes();
        Path filePath = uploadPath.resolve(fileName);
        Files.write(filePath, fileContent);

        Path filePath2 = uploadPath2.resolve(fileName);
        Files.write(filePath2, fileContent);


        System.out.println( "fileName : "+ fileName);
        System.out.println("uploadPath : " + uploadPath);
        System.out.println("uploadPath2 : " + uploadPath2);
        System.out.println("fileContent : " + fileContent);
        System.out.println("filePath : " + filePath);
        System.out.println("filePath2 : " + filePath2);

        return fileName;
    }
}
