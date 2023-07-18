package com.example.demo.repository;

import com.example.demo.vo.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CommentRepository {

    private final CommentMapper commentMapper;

    public Comment saveComment(int dailyPostId, Comment comment){
        commentMapper.saveComment(dailyPostId,comment);
        return comment;
    }
}
