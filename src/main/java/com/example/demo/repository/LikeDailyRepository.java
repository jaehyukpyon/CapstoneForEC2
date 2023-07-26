package com.example.demo.repository;

import com.example.demo.dto.DetailDailyPostDto;
import com.example.demo.dto.LikeNum;
import com.example.demo.mapper.DailyPostMapper;
import com.example.demo.mapper.LikeDailyMapper;
import com.example.demo.vo.LikeDailyPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeDailyRepository {

    private final LikeDailyMapper likeDailyMapper;

    public LikeDailyPost increaseLikeNum(LikeDailyPost likeDailyPost){

        likeDailyMapper.increaseLikeNum(likeDailyPost);
        return likeDailyPost;
    }

    public int deleteLikeNum(int dailyPostId, Integer userId){
        int t = likeDailyMapper.deleteLikeNum(dailyPostId, userId);
        return t;
    }
    public int findLikeNum(){
        int likeNum = likeDailyMapper.findLikeNum();
        return likeNum;
    }
}
