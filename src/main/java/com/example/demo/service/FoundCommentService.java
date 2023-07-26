package com.example.demo.service;

import com.example.demo.domain.FindComment;
import com.example.demo.domain.FoundComment;
import com.example.demo.repository.FoundCommentRepository;
import com.example.demo.vo.AuthUserVo_j;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FoundCommentService {

    private final FoundCommentRepository commentRepository;

    public FoundComment saveComment(int foundId, FoundComment comment){
        AuthUserVo_j auth = (AuthUserVo_j) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = auth.getId();
        comment.setUserId(id);
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        comment.setRegisterAt(now);
        comment.setFoundId(foundId);
        commentRepository.saveComment(foundId,comment);
        return comment;
    }

    public List<FoundComment> getList(int foundId) {
        List<FoundComment> list = commentRepository.getList(foundId);
        return list;
    }
}
