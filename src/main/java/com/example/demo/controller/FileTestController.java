package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@RestController
@RequestMapping("/noauth")
@RequiredArgsConstructor
public class FileTestController {

    @Value("${file.upload.directory}")
    private String itemImgLocation;

    @PostMapping("/fileupload")
    public ResponseEntity<?> fileupload(@RequestParam("file") MultipartFile file) {
        saveItemImg(file);
        return ResponseEntity.ok("good");
    }

    public void saveItemImg(MultipartFile itemImgFile) {
        String oriImgName = itemImgFile.getOriginalFilename();
        try {
            String imgName = uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) {
        String returnSavedFileName = "";
        try {
            UUID uuid = UUID.randomUUID();
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String savedFileName = uuid.toString() + extension;
            String fileUploadFullUrl = uploadPath + "/" + savedFileName;
            FileOutputStream fileOutputStream = new FileOutputStream(fileUploadFullUrl);
            fileOutputStream.write(fileData);
            returnSavedFileName = savedFileName;
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnSavedFileName;
    }


    @GetMapping("/filedownload")
    public ResponseEntity<byte[]> downloadfile() {
        File file = new File(itemImgLocation + "/" + "0e92dc17-e473-4e62-b71b-73337b96dbe9.png");
        ResponseEntity<byte[]> result = null;

        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
