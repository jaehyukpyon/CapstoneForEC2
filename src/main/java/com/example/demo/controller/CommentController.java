package com.example.demo.controller;

import com.example.demo.dto.ThumbnailDailyPostDto;
import com.example.demo.dto.UpdateDailyPostDto;
import com.example.demo.service.CommentService;
import com.example.demo.vo.AuthUserVo_j;
import com.example.demo.vo.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/comment")
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment, Authentication authentication){
        AuthUserVo_j authUserVo = (AuthUserVo_j) authentication.getPrincipal();
        Integer userId = authUserVo.getId();
        comment.setUserId(userId);
        commentService.saveComment(comment);

        return new ResponseEntity<>(comment,HttpStatus.OK);
    }

    @PutMapping("/api/comment/{dailyPostId}/edit/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable int dailyPostId,@PathVariable int commentId,@RequestBody Comment comment,Authentication authentication) {
        AuthUserVo_j authUserVo = (AuthUserVo_j) authentication.getPrincipal();
        Integer userId = authUserVo.getId();
        comment.setUserId(userId);
        comment.setDailyPostId(dailyPostId);
        comment.setId(commentId);

        commentService.updateComment(dailyPostId, comment);
        return new ResponseEntity<>(comment,HttpStatus.OK);
    }

    @GetMapping("/noauth/comment/{dailyPostId}")
    public ResponseEntity<List<Comment>>findComment(@PathVariable int dailyPostId) {
        List<Comment> comment = commentService.findComment(dailyPostId);
        return new ResponseEntity<>(comment,HttpStatus.OK);
    }

    @DeleteMapping("/api/comment/{commentId}")
    public ResponseEntity<?> deleteDailyPost(@PathVariable int commentId){
        Map<String, Object> map = new HashMap<>();

        int t = commentService.deleteComment(commentId);
        map.put("commentId", commentId);
        if (t > 0) {
            map.put("message", "댓글이 정상적으로 삭제되었습니다.");
            return ResponseEntity.ok(map);
        } else {
            map.put("message", "댓글 삭제 중 오류 발생. 댓글이 이미 삭제되었을 수 있습니다.");
            return ResponseEntity.badRequest().body(map);
        }
    }
}
