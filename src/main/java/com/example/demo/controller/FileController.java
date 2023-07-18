package com.example.demo.controller;

import com.example.demo.response.FileResponse;
import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;
    @PostMapping("upload")
    public ResponseEntity<FileResponse> fileUpload(
        @RequestParam("image") MultipartFile image) {
        String fileName = null;
        try {
            fileName = this.fileService.uploadImage(path,image);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new FileResponse(null, "image is not uploaded"),HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(new FileResponse(fileName, "image is successfully uploaded"),HttpStatus.OK);
    }
}
