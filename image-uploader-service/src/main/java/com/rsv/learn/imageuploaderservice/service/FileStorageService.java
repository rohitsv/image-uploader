package com.rsv.learn.imageuploaderservice.service;

import com.rsv.learn.imageuploaderservice.dto.FileUploadResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    FileUploadResponse uploadFile(MultipartFile file);

    Resource getFile(String fileName);

    boolean deleteAll();
}
