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
public class Found {
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
