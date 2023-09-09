package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/file/ppp")
    public void ppp(HttpServletRequest request) {
        String path = "/temp";
        System.out.println(request.getServletContext().getRealPath("/"));

        ServletContext context = request.getSession().getServletContext();
        System.out.println(context.getRealPath(path));
        System.out.println();
    }

    @PostMapping("/fileupload")
    public ResponseEntity<?> fileupload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String path = File.separator + "temp";
        ServletContext context = request.getSession().getServletContext();
        String realPath = context.getRealPath(path);
        System.out.println(realPath);
        System.out.println();

        saveItemImg(file, realPath);
        return ResponseEntity.ok("good");
    }

    public void saveItemImg(MultipartFile itemImgFile, String realPath) {
        File savePath = new File(realPath);
        if (savePath.exists() == false) {
            System.out.println("false!!!");
            savePath.mkdirs();
        }
        String oriImgName = itemImgFile.getOriginalFilename();
        try {
            String imgName = uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes(), realPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData, String realPath) {
        String returnSavedFileName = "";
        try {
            UUID uuid = UUID.randomUUID();
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String savedFileName = uuid.toString() + extension;
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


    @GetMapping("/filedownload")
    public ResponseEntity<byte[]> downloadfile(HttpServletRequest request) {
        String path = File.separator + "temp";
        ServletContext context = request.getSession().getServletContext();
        String realPath = context.getRealPath(path);
        System.out.println(realPath);


        //File file = new File(itemImgLocation + "/" + "0e92dc17-e473-4e62-b71b-73337b96dbe9.png");

        File file = new File(realPath + File.separator + "fc8c2d9c-a8ac-4141-adbd-5fce72d554ee.png");
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
