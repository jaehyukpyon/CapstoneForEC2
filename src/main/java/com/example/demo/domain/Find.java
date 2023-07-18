package com.example.demo.domain;

import lombok.*;

import java.sql.Timestamp;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Find {
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
