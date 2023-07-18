package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Getter
@Setter
public class FoundDto {
    private int foundId;
    private int userId;
    private String title;
    private String content;
    private String image;
    private String place;
    private String time;
    private Timestamp registerAt;
    private Timestamp updatedAt;

}
