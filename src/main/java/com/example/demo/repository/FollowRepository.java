package com.example.demo.repository;
import com.example.demo.dto.FollowFindDto;
import com.example.demo.mapper.FollowMapper;
import com.example.demo.vo.Follow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FollowRepository {

    final private FollowMapper followMapper;


    public Follow createFollow(Follow follow){
        followMapper.createFollow(follow);
        //follow.setDailyPostId(dailyPostId);
        //follow.setFollowUserId(followUserId);
        return follow;
    }

    public void deleteFollow(int followId){
        followMapper.deleteFollow(followId);
    }

    public List<FollowFindDto> findFollow(int dailyPostId){
        List<FollowFindDto> follow= followMapper.findFollow(dailyPostId);
        return follow;
    }
}
