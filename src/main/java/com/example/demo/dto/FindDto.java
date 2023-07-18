package com.example.demo.dto;

import com.example.demo.domain.Find;
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
    private Timestamp registerAt;
    private Timestamp updatedAt;

}
