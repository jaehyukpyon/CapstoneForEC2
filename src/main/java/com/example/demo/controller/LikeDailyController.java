package com.example.demo.controller;


import com.example.demo.dto.LikeNum;
import com.example.demo.service.DailyPostService;
import com.example.demo.service.LikeDailyService;
import com.example.demo.vo.AuthUserVo_j;
import com.example.demo.vo.LikeDailyPost;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class LikeDailyController {

    private final LikeDailyService likeDailyService;

    @PostMapping("/api/like")
    public LikeDailyPost increaseLikeNum(@RequestBody LikeDailyPost likeDailyPost, Authentication authentication){
        AuthUserVo_j authUserVo = (AuthUserVo_j) authentication.getPrincipal();
        Integer userId = authUserVo.getId();
        likeDailyPost.setLikeUserId(userId);

        likeDailyService.increaseLikeNum(likeDailyPost);
        return likeDailyPost;
    }

    @DeleteMapping("/api/like/{dailyPostId}")
    public int deleteLikeNum(@PathVariable int dailyPostId,Authentication authentication){
        AuthUserVo_j authUserVo = (AuthUserVo_j) authentication.getPrincipal();
        Integer userId = authUserVo.getId();
        int t = likeDailyService.deleteLikeNum(dailyPostId, userId);
        return t;
    }

    @GetMapping("/noauth/like/findLikeNum")
    public int findLikeNum() {
        int likeNum = likeDailyService.findLikeNum();
        return likeNum;
    }


}
