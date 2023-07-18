package com.example.demo.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Comment {

    private int commentId;
    private String content;
    private int userId;
    private int dailyPostId;
    private Timestamp registerAt;
    private Timestamp updatedAt;
}
