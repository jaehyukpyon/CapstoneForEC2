package com.example.demo.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FindComment {

    private int commentId;
    private String content;
    private int userId;
    private int findId;
    private Timestamp registerAt;
    private Timestamp updatedAt;
}
