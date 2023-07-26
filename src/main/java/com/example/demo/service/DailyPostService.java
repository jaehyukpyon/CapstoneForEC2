package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.repository.DailyPostRepository;
import com.example.demo.vo.DailyPost;
import com.example.demo.vo.Follow;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DailyPostService {

    private final DailyPostRepository dailyPostRepository;

    public DailyPost saveDailyPost(DailyPost dailyPost){
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        //LocalDateTime now = LocalDateTime.now();

        dailyPost.setRegisterAt(now);

        dailyPostRepository.saveDailyPost(dailyPost);

        return dailyPost;
    }

    public UpdateDailyPostDto updateDailyPost(int dailyPostId, UpdateDailyPostDto updateDailyPostDto){
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        //updated createdAt 삽입
        updateDailyPostDto.setUpdatedAt(now);
        //daily PostId 삽입
        updateDailyPostDto.setId(dailyPostId);


        dailyPostRepository.updateDailyPost(dailyPostId,updateDailyPostDto);
        return updateDailyPostDto;
    }

    public List<ThumbnailDailyPostDto> findThumbnail(){
        List<ThumbnailDailyPostDto> thumbnail = dailyPostRepository.findThumbnail();

        return thumbnail;
    }

    public DetailDailyPostDto findById(int dailyPostId){
        DetailDailyPostDto detail = dailyPostRepository.findById(dailyPostId);
        return detail;
    }



    public int deleteDailyPost(int dailyPostId){

        int t = dailyPostRepository.deleteDailyPost(dailyPostId);
        return t;
    }







}
