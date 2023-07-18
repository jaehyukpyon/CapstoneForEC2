package com.example.demo.controller;

import com.example.demo.service.CommentService;
import com.example.demo.vo.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("{dailyPostId}")
    public Comment saveComment(@PathVariable int dailyPostId, @RequestBody Comment comment){
        commentService.saveComment(dailyPostId,comment);
        return comment;
    }
}
