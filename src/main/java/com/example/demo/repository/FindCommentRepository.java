package com.example.demo.repository;

import com.example.demo.domain.FindComment;
import com.example.demo.mapper.FindCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FindCommentRepository {

    private final FindCommentMapper commentMapper;

    public FindComment saveComment(int findId, FindComment comment){
        commentMapper.saveComment(findId,comment);
        return comment;
    }
}
