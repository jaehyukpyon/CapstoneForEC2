package com.example.demo.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FoundComment {

    private int commentId;
    private String content;
    private int userId;
    private int foundId;
    private Timestamp registerAt;
    private Timestamp updatedAt;
}
