package com.example.demo.repository;

import com.example.demo.dto.ThumbnailDailyPostDto;
import com.example.demo.dto.UpdateDailyPostDto;
import com.example.demo.vo.DailyPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DailyPostRepository {

    private final DailyPostMapper dailyPostMapper;

    public DailyPost saveDailyPost(DailyPost  dailyPost){
        dailyPostMapper.saveDailyPost(dailyPost);
        return dailyPost;
    }


    public UpdateDailyPostDto updateDailyPost(int dailyPostId, UpdateDailyPostDto dailyPostDto){
        dailyPostMapper.updateDailyPost(dailyPostId,dailyPostDto);
        return dailyPostDto;
    }

    public List<ThumbnailDailyPostDto> findThumbnail(ThumbnailDailyPostDto thumbnailDailyPostDto){
        List<ThumbnailDailyPostDto> thumbnail = dailyPostMapper.findThumbnail(thumbnailDailyPostDto);

        return thumbnail;
    }

    public void deleteDailyPost(int dailyPostId){
        dailyPostMapper.deleteDailyPost(dailyPostId);

    }
}
