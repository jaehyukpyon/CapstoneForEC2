package com.example.demo.repository;

import com.example.demo.dto.*;
import com.example.demo.mapper.DailyPostMapper;
import com.example.demo.mapper.LikeDailyMapper;
import com.example.demo.vo.DailyPost;
import com.example.demo.vo.Follow;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DailyPostRepository {

    private final DailyPostMapper dailyPostMapper;
    private final LikeDailyMapper likeDailyMapper;

    public DailyPost saveDailyPost(DailyPost dailyPost){

        //int likeNum = likeDailyMapper.findLikeNum();
        //dailyPost.setLikeNum(likeNum);
        dailyPostMapper.saveDailyPost(dailyPost);
        return dailyPost;
    }


    public UpdateDailyPostDto updateDailyPost(int dailyPostId, UpdateDailyPostDto dailyPostDto){
        dailyPostMapper.updateDailyPost(dailyPostId,dailyPostDto);
        return dailyPostDto;
    }

    public List<ThumbnailDailyPostDto> findThumbnail(){
        List<ThumbnailDailyPostDto> thumbnail = dailyPostMapper.findThumbnail();


        return thumbnail;
    }

    public DetailDailyPostDto findById(int dailyPostId){
        DetailDailyPostDto detail = dailyPostMapper.findById(dailyPostId);
        return detail;
    }

    public int deleteDailyPost(int dailyPostId){
        int t = dailyPostMapper.deleteDailyPost(dailyPostId);
        return t;
    }





}
