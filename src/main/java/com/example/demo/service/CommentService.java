package com.example.demo.service;

import com.example.demo.dto.ThumbnailDailyPostDto;
import com.example.demo.dto.UpdateCommentDto;
import com.example.demo.dto.UpdateDailyPostDto;
import com.example.demo.repository.CommentRepository;
import com.example.demo.vo.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment saveComment(Comment comment){
        Timestamp now = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        //updated createdAt 삽입
        comment.setRegisterAt(now);
        commentRepository.saveComment(comment);
        return comment;
    }


    public Comment updateComment(int dailyPostId, Comment comment){
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        //updated createdAt 삽입
        comment.setUpdatedAt(now);
        commentRepository.updateComment(dailyPostId,comment);
        return comment;
    }

    public List<Comment> findComment(int dailyPostId){
        List<Comment> comment = commentRepository.findComment(dailyPostId);
        return comment;
    }
    public int deleteComment(int commentId){
        int t = commentRepository.deleteComment(commentId);
        return t;
    }


}
