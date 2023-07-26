package com.example.demo.vo;


import lombok.Data;

@Data
public class LikeDailyPost {
    private int id;
    private int dailyPostId;
    private int likeUserId;
}
