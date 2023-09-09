package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileSaveService {

    @Value("${file.upload.directory}")
    private String itemImgLocation;

    public String saveItemImg(MultipartFile itemImgFile, String realPath) {
        File savePath = new File(realPath);
        if (savePath.exists() == false) {
            System.out.println("false!!!");
            savePath.mkdirs();
        }

        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        try {
            imgName = uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes(), realPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgName;
    }

    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData, String realPath) {
        String returnSavedFileName = "";
        try {
            UUID uuid = UUID.randomUUID();
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String savedFileName = uuid.toString() + extension;
            //String fileUploadFullUrl = uploadPath + "/" + savedFileName;
            String fileUploadFullUrl = realPath + File.separator + savedFileName;
            FileOutputStream fileOutputStream = new FileOutputStream(fileUploadFullUrl);
            fileOutputStream.write(fileData);
            returnSavedFileName = savedFileName;
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnSavedFileName;
    }
}
