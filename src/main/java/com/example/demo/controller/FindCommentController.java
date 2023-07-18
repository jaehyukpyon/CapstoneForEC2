package com.example.demo.controller;

import com.example.demo.service.FindCommentService;
import com.example.demo.domain.FindComment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/find/")
public class FindCommentController {

    private final FindCommentService commentService;

    @PostMapping("{findId}/comment")
    public FindComment saveComment(@PathVariable int findId, @RequestBody FindComment comment){
        commentService.saveComment(findId,comment);
        return comment;
    }
}
