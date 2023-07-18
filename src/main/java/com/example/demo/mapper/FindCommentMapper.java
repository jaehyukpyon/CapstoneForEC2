package com.example.demo.mapper;

import com.example.demo.domain.FindComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FindCommentMapper {

    void saveComment(@Param("findId")int findIdComment, @Param("comment") FindComment comment);

    void update(@Param("findId")int findId, @Param("comment") FindComment comment);
}
