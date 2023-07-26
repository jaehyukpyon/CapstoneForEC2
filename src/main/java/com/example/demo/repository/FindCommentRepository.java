package com.example.demo.repository;

import com.example.demo.domain.FindComment;
import com.example.demo.mapper.FindCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class FindCommentRepository {

    private final FindCommentMapper commentMapper;

    public FindComment saveComment(int findId, FindComment comment){
        commentMapper.saveComment(comment);
        return comment;
    }

    public List<FindComment> getList(int findId) {
        List<FindComment> list = commentMapper.getList(findId);
        return list;
    }
}
