package com.library.service.admin;

import com.library.dto.admin._normal.BookDTO;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileUploadService {

    private final ResourceLoader resourceLoader;

    public FileUploadService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void saveUploadedFile(MultipartFile file, BookDTO bookDTO) throws IOException {
        if (file != null && !file.isEmpty()) {
            String uploadsDir = "/static/documents/";
            String realPathToUploads = resourceLoader.getResource("classpath:").getFile().getAbsolutePath() + uploadsDir;
            File uploadsDirFile = new File(realPathToUploads);

            if (!uploadsDirFile.exists()) {
                uploadsDirFile.mkdirs();
            }

            String orgName = file.getOriginalFilename();
            String fileName = UUID.randomUUID() + "-" + orgName;
            Path filePath = Paths.get(realPathToUploads + fileName);

            try {
                Files.write(filePath, file.getBytes());
                bookDTO.setImg(fileName);
            } catch (IOException e) {
                // 파일 저장 실패 처리
                e.printStackTrace();
                throw new IOException("파일 업로드 중 오류 발생");
            }
        }
    }
}
