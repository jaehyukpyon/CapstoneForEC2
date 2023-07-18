package com.example.demo.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ThumbnailDailyPostDto {

    private int dailyPostId;
    private int userId;
    private String title;
    private String image;
    private Timestamp registerAt;
    private Timestamp updatedAt;
}
