package com.example.demo.controller;

import com.example.demo.domain.FindComment;
import com.example.demo.domain.FoundComment;
import com.example.demo.service.FoundCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/found/comment")
public class FoundCommentController {

    private final FoundCommentService commentService;

    @PostMapping("{foundId}/save")
    public FoundComment saveComment(@PathVariable int foundId, @RequestBody FoundComment comment){
        commentService.saveComment(foundId,comment);
        return comment;
    }
    @GetMapping("/list/{foundId}")
    public List<FoundComment> getList(@PathVariable int foundId){
        List<FoundComment> list = commentService.getList(foundId);
        return list;
    }
}
