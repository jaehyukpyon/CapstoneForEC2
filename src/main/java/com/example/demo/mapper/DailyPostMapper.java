package com.example.demo.mapper;

import com.example.demo.dto.*;
import com.example.demo.vo.DailyPost;
import com.example.demo.vo.Follow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
public interface DailyPostMapper {

    void saveDailyPost(@Param("dailyPost") DailyPost dailyPost);

    void updateDailyPost(@Param("id")int id, @Param("dailyPostDto") UpdateDailyPostDto dailyPostDt);

    List<ThumbnailDailyPostDto> findThumbnail();

    DetailDailyPostDto findById(int id);

    int deleteDailyPost(int id);


}
