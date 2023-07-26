package com.example.demo.dto;

import lombok.Data;

@Data
public class FollowFindDto{

    private int id;
    private int followUserId;
    private int dailyPostId;
    private String nickname;
    private String email;

}
