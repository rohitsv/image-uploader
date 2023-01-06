package com.rsv.learn.imageuploaderservice.service;

import com.rsv.learn.imageuploaderservice.config.FileUploadProperties;
import com.rsv.learn.imageuploaderservice.dto.FileUploadResponse;
import com.rsv.learn.imageuploaderservice.exception.FileNotFoundException;
import com.rsv.learn.imageuploaderservice.exception.FileStorageClientException;
import com.rsv.learn.imageuploaderservice.exception.FileStorageInternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Optional;

import static org.springframework.util.FileSystemUtils.deleteRecursively;

@Service
public class LocalFileSystemStorageService implements FileStorageService {
    private final Path localFileStorageDir;

    @Autowired
    public LocalFileSystemStorageService(FileUploadProperties fileUploadProperties) {
        localFileStorageDir = Path.of(fileUploadProperties.getLocation())
                .toAbsolutePath()
                .normalize();
    }

    @PostConstruct
    public void init() {
        try {
            if(!localFileStorageDir.toFile().exists())
                Files.createDirectory(localFileStorageDir);
        } catch (IOException e) {
            throw new FileStorageInternalException("Failed to create local file storage directory");
        }
    }

    @Override
    public FileUploadResponse uploadFile(MultipartFile receivedFile) {
        String receivedFileName = Optional.ofNullable(receivedFile.getOriginalFilename())
                .orElseThrow(() -> new FileStorageClientException("Received file name is null/empty"));
        Path localFilePath = localFileStorageDir.resolve(receivedFileName);
        try {
            Files.copy(receivedFile.getInputStream(), localFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileStorageInternalException("Failed to read received file");
        }
        return FileUploadResponse.builder()
                .fileName(receivedFileName)
                .message("File uploaded!")
                .build();
    }

    @Override
    public Resource getFile(String fileName) {
        Path filePath = localFileStorageDir.resolve(fileName);
        Resource resource = null;
        try {
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not find file with specified name");
        }

        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new FileNotFoundException("Could not find file with specified name");
        }
    }

    @Override
    public boolean deleteAll() {
        return Arrays.stream(localFileStorageDir.toFile()
                .listFiles())
                .allMatch(FileSystemUtils::deleteRecursively);
    }
}
