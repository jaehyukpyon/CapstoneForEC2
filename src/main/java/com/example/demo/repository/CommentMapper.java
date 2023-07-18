package com.example.demo.repository;

import com.example.demo.vo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {

    void saveComment(@Param("dailyPostId")int dailyPostIdComment, @Param("comment") Comment comment);

    void update(@Param("dailyPostId")int dailyPostId, @Param("comment") Comment comment);
}
