package com.example.demo.mapper;

import com.example.demo.dto.ThumbnailDailyPostDto;
import com.example.demo.vo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    void saveComment(Comment comment);

    void updateComment(@Param("dailyPostId")int dailyPostId, @Param("comment") Comment comment);

    List<Comment> findComment(int dailyPostId);

    int deleteComment(int id);

}
