package com.example.demo.mapper;

import com.example.demo.dto.FollowFindDto;
import com.example.demo.vo.Follow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper {
    void createFollow(Follow follow);

    void deleteFollow(int followId);

    List<FollowFindDto> findFollow(int dailyPostId);
}
