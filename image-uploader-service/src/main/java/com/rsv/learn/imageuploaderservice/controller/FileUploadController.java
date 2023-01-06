package com.rsv.learn.imageuploaderservice.controller;

import com.rsv.learn.imageuploaderservice.dto.FileUploadResponse;
import com.rsv.learn.imageuploaderservice.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileUploadController {
    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fileStorageService.uploadFile(file));
    }

    @GetMapping()
    public ResponseEntity<Resource> downloadFile(@RequestParam("file") String fileName) {
        Resource resource = fileStorageService.getFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment; filename=\"%s\"", resource.getFilename()))
                .body(resource);
    }

}
