package com.example.demo.controller;

import com.example.demo.domain.FoundComment;
import com.example.demo.service.FoundCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/found/")
public class FoundCommentController {

    private final FoundCommentService commentService;

    @PostMapping("{foundId}/comment")
    public FoundComment saveComment(@PathVariable int foundId, @RequestBody FoundComment comment){
        commentService.saveComment(foundId,comment);
        return comment;
    }
}
