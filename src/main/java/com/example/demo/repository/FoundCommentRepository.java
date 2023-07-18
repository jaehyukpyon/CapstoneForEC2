package com.example.demo.repository;

import com.example.demo.domain.FoundComment;
import com.example.demo.mapper.FoundCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FoundCommentRepository {

    private final FoundCommentMapper commentMapper;

    public FoundComment saveComment(int foundId, FoundComment comment){
        commentMapper.saveComment(foundId,comment);
        return comment;
    }
}
