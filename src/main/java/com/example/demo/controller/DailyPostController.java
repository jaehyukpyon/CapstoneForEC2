package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.DailyPostService;
import com.example.demo.service.FileSaveService;
import com.example.demo.vo.AuthUserVo_j;
import com.example.demo.vo.DailyPost;
import com.example.demo.vo.Follow;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class DailyPostController {

    private final DailyPostService dailyPostService;

    private final FileSaveService fileSaveService;

    @Value("${url}")
    private String url;

    @Value("${file.upload.directory}")
    private String itemImgLocation;

    @PostMapping("/api/dailyPost")
    public ResponseEntity<DailyPost> saveDailyPost(DailyPost dailyPost, @RequestParam("photo") MultipartFile photo, Authentication authentication) {

        AuthUserVo_j authUserVo = (AuthUserVo_j) authentication.getPrincipal();
        Integer userId = authUserVo.getId();
        dailyPost.setUserId(userId);
        String photoName = url + fileSaveService.saveItemImg(photo);
        dailyPost.setPhotourl(photoName);
        dailyPostService.saveDailyPost(dailyPost);

        return new ResponseEntity<>(dailyPost, HttpStatus.OK);
    }

    @GetMapping("/noauth/downloadphoto")
    public ResponseEntity<byte[]> downloadfile(@RequestParam("photo") String photourl) {
        File file = new File(itemImgLocation + "/" + photourl);
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

    @PostMapping("/api/dailyPost/{dailyPostId}/edit")
    public ResponseEntity<UpdateDailyPostDto> updateDailyPost(@PathVariable int dailyPostId, @RequestBody UpdateDailyPostDto updateDailyPostDto,Authentication authentication) {

        AuthUserVo_j authUserVo = (AuthUserVo_j) authentication.getPrincipal();
        Integer userId = authUserVo.getId();
        updateDailyPostDto.setUserId(userId);
        dailyPostService.updateDailyPost(dailyPostId,updateDailyPostDto);
        return new ResponseEntity<>(updateDailyPostDto,HttpStatus.OK);
    }

    @GetMapping("/noauth/dailyPost")
    public ResponseEntity<List<ThumbnailDailyPostDto>> findThumbnail() {
        List<ThumbnailDailyPostDto> thumbnail = dailyPostService.findThumbnail();
        return new ResponseEntity <>(thumbnail,HttpStatus.OK);
    }


    @GetMapping("/noauth/dailyPost/{dailyPostId}")
    public ResponseEntity<DetailDailyPostDto> findById(@PathVariable int dailyPostId) {
        DetailDailyPostDto detail= dailyPostService.findById(dailyPostId);
            return new ResponseEntity <> (detail,HttpStatus.OK);
    }

    @DeleteMapping("/api/dailyPost/{dailyPostId}")
    public ResponseEntity<?> deleteDailyPost(@PathVariable int dailyPostId) {
        Map<String, Object> map = new HashMap<>();

        int t = dailyPostService.deleteDailyPost(dailyPostId);
        map.put("dailyPostId", dailyPostId);
        if (t > 0) {
            map.put("message", "게시물이 정상적으로 삭제되었습니다.");
            return ResponseEntity.ok(map);
        } else {
            map.put("message", "게시물 삭제 중 오류 발생. 게시물이 이미 삭제되었을 수 있습니다.");
            return ResponseEntity.badRequest().body(map);
        }
    }
}
