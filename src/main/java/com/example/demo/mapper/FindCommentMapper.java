package com.example.demo.mapper;

import com.example.demo.domain.FindComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FindCommentMapper {

    void saveComment(@Param("comment") FindComment comment);

    List<FindComment> getList(@Param("findId")int findId);
}
