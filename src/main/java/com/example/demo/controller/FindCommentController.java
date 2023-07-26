package com.example.demo.controller;

import com.example.demo.service.FindCommentService;
import com.example.demo.domain.FindComment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.demo.domain.FindComment;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/find/comment")
public class FindCommentController {

    private final FindCommentService commentService;

    @PostMapping("{findId}/save")
    public FindComment saveComment(@PathVariable int findId, @RequestBody FindComment comment) {
        commentService.saveComment(findId, comment);
        return comment;
    }

    @GetMapping("/list/{findId}")
    public List<FindComment> getList(@PathVariable int findId) {
        List<FindComment> list = commentService.getList(findId);
        return list;
    }

}
