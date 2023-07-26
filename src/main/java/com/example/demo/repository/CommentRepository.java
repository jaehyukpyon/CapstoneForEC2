package com.example.demo.repository;

import com.example.demo.dto.ThumbnailDailyPostDto;
import com.example.demo.dto.UpdateDailyPostDto;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.vo.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CommentRepository {

    private final CommentMapper commentMapper;

    public Comment saveComment(Comment comment){


        commentMapper.saveComment(comment);
        return comment;
    }

    public Comment updateComment(int dailyPostId, Comment comment){
        commentMapper.updateComment(dailyPostId,comment);
        return comment;
    }

    public List<Comment> findComment(int dailyPostId){
        List<Comment> comment = commentMapper.findComment(dailyPostId);

        return comment;
    }

    public int deleteComment(int commentId){
        int t = commentMapper.deleteComment(commentId);
        return t;
    }



}
