package com.example.demo.dto;

import com.example.demo.domain.Find;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Data
@Getter
@Setter
public class FindDto {
    private int findId;
    private int userId;
    private String title;
    private String content;
    private String image;
    private String place;
    private String time;
    private String money;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp registerAt;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp updatedAt;
}
