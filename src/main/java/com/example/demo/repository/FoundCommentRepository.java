package com.example.demo.repository;

import com.example.demo.domain.FoundComment;
import com.example.demo.mapper.FoundCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class FoundCommentRepository {

    private final FoundCommentMapper commentMapper;

    public FoundComment saveComment(int foundId, FoundComment comment){
        commentMapper.saveComment(comment);
        return comment;
    }

    public List<FoundComment> getList(int foundId) {
        List<FoundComment> list = commentMapper.getList(foundId);
        return list;
    }
}
