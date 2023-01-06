package com.rsv.learn.imageuploaderservice.controller;

import com.rsv.learn.imageuploaderservice.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/files/admin/storage")
public class FileUploadAdminController {

    @Autowired
    private FileStorageService fileStorageService;

    @DeleteMapping()
    public ResponseEntity<Boolean> test() {
        return ResponseEntity.ok().body(fileStorageService.deleteAll());
    }

}
