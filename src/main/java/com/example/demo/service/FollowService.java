package com.example.demo.service;

import com.example.demo.dto.FollowFindDto;
import com.example.demo.repository.FollowRepository;
import com.example.demo.vo.Follow;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FollowService {

    final private FollowRepository followRepository;

    public Follow createFollow(Follow follow){
        followRepository.createFollow(follow);
        return follow;
    }


    public void deleteFollow(int followId){
        followRepository.deleteFollow(followId);
    }


    public List<FollowFindDto> findFollow(int dailyPostId){
        List<FollowFindDto> follow= followRepository.findFollow(dailyPostId);
        return follow;
    }
}
