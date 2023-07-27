package com.example.demo.controller;


import com.example.demo.dto.LikeNum;
import com.example.demo.service.DailyPostService;
import com.example.demo.service.LikeDailyService;
import com.example.demo.vo.AuthUserVo_j;
import com.example.demo.vo.LikeDailyPost;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> deleteLikeNum(@PathVariable int dailyPostId, Authentication authentication){
        Map<String, Object> map = new HashMap<>();

        AuthUserVo_j authUserVo = (AuthUserVo_j) authentication.getPrincipal();
        Integer userId = authUserVo.getId();

        int t = likeDailyService.deleteLikeNum(dailyPostId, userId);
        map.put("dailyPostId", dailyPostId);
        if (t > 0) {
            map.put("message", "좋아요가 모두 삭제되었습니다.");
            return ResponseEntity.ok(map);
        } else {
            map.put("message", "좋아요 삭제 중 오류 발생. 이미 모든 좋아요가 삭제되었을 수 있습니다.");
            return ResponseEntity.badRequest().body(map);
        }
    }

    @GetMapping("/noauth/like/findLikeNum")
    public int findLikeNum() {
        int likeNum = likeDailyService.findLikeNum();
        return likeNum;
    }
}
