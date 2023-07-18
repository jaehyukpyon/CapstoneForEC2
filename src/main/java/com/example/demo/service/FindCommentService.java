package com.example.demo.service;

import com.example.demo.dto.AuthUserDto_j;
import com.example.demo.repository.FindCommentRepository;
import com.example.demo.domain.FindComment;
import com.example.demo.vo.AuthUserVo_j;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class FindCommentService {

    private final FindCommentRepository commentRepository;

    public FindComment saveComment(int findId, FindComment comment){
        AuthUserVo_j auth = (AuthUserVo_j) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = auth.getId();
        comment.setUserId(id);
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        comment.setRegisterAt(now);
        comment.setFindId(findId);
        commentRepository.saveComment(findId,comment);
        return comment;
    }
}
