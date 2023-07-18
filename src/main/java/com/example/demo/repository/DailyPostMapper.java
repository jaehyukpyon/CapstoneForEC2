package com.example.demo.repository;

import com.example.demo.dto.ThumbnailDailyPostDto;
import com.example.demo.dto.UpdateDailyPostDto;
import com.example.demo.vo.DailyPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DailyPostMapper {

    void saveDailyPost(DailyPost dailyPost);

    void updateDailyPost(@Param("dailyPostId")int dailyPostId, @Param("dailyPostDto") UpdateDailyPostDto dailyPostDt);

    List<ThumbnailDailyPostDto> findThumbnail(ThumbnailDailyPostDto thumbnailDailyPostDto);

    void deleteDailyPost(int dailyPostId);
}
