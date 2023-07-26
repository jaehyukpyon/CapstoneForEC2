package com.example.demo.mapper;

import com.example.demo.vo.DailyPost;
import com.example.demo.vo.LikeDailyPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.Authentication;

@Mapper
public interface LikeDailyMapper {
    void increaseLikeNum(@Param("likeDailyPost") LikeDailyPost likeDailyPost);

    int deleteLikeNum(@Param("dailyPostId")int dailyPostId,@Param("userId")Integer userId);

    int findLikeNum();

}
