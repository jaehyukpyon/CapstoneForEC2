package com.example.demo.service;

import com.example.demo.dto.LikeNum;
import com.example.demo.repository.DailyPostRepository;
import com.example.demo.repository.LikeDailyRepository;
import com.example.demo.vo.LikeDailyPost;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class LikeDailyService {

    private final LikeDailyRepository likeDailyRepository;

    public LikeDailyPost increaseLikeNum(LikeDailyPost likeDailyPost){
        LikeDailyPost likeNum = likeDailyRepository.increaseLikeNum(likeDailyPost);
        return likeNum;
    }

    public int deleteLikeNum(int dailyPostId, Integer userId){
        int t = likeDailyRepository.deleteLikeNum(dailyPostId, userId);
        return t;
    }

    public int findLikeNum(){
        int likeNum = likeDailyRepository.findLikeNum();
        return likeNum;
    }
}
