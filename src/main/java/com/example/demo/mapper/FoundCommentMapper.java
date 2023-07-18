package com.example.demo.mapper;

import com.example.demo.domain.FoundComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FoundCommentMapper {

    void saveComment(@Param("foundId")int foundIdComment, @Param("comment") FoundComment comment);

    void update(@Param("foundId")int foundId, @Param("comment") FoundComment comment);
}
