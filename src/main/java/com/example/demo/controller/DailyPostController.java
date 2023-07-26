package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.DailyPostService;
import com.example.demo.vo.AuthUserVo_j;
import com.example.demo.vo.DailyPost;
import com.example.demo.vo.Follow;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class DailyPostController {

    private final DailyPostService dailyPostService;

    @PostMapping("/api/dailyPost")
    public ResponseEntity<DailyPost> saveDailyPost(@RequestBody DailyPost dailyPost, Authentication authentication) {

        AuthUserVo_j authUserVo = (AuthUserVo_j) authentication.getPrincipal();
        Integer userId = authUserVo.getId();
        dailyPost.setUserId(userId);
        dailyPostService.saveDailyPost(dailyPost);

        return new ResponseEntity<>(dailyPost, HttpStatus.OK);
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
    public ResponseEntity<DetailDailyPostDto> findById(@PathVariable int dailyPostId){
        DetailDailyPostDto detail= dailyPostService.findById(dailyPostId);
            return new ResponseEntity <> (detail,HttpStatus.OK);
    }

    @DeleteMapping("/api/dailyPost/{dailyPostId}")
    public int deleteDailyPost(@PathVariable int dailyPostId){

        int t = dailyPostService.deleteDailyPost(dailyPostId);
        return t;
    }




}
