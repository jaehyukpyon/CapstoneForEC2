package com.example.demo.controller;


import com.example.demo.dto.FollowFindDto;
import com.example.demo.service.FollowService;
import com.example.demo.vo.AuthUserVo_j;
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
public class FollowController {

    final private FollowService followService;
    @PostMapping("/api/follow")
    public ResponseEntity<Follow> createFollow(@RequestBody Follow follow, Authentication authentication){

        AuthUserVo_j authUserVo = (AuthUserVo_j) authentication.getPrincipal();
        Integer userId = authUserVo.getId();
        follow.setFollowUserId(userId);
        followService.createFollow(follow);
        return new ResponseEntity <>(follow, HttpStatus.OK);
    }

    @DeleteMapping("/api/follow/{followId}")
    public void deleteFollow(@PathVariable int followId){
        followService.deleteFollow(followId);
    }


    //dailyPost repository에서 좋아요 수 넣는 용도
    @GetMapping("/noauth/follow/{dailyPostId}")
    public ResponseEntity<List<FollowFindDto>> findFollow(@PathVariable int dailyPostId){
        List<FollowFindDto> follow= followService.findFollow(dailyPostId);
        return new ResponseEntity<> (follow,HttpStatus.OK);
    }

}
