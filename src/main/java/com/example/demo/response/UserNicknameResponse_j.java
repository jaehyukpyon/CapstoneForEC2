package com.example.demo.response;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserNicknameResponse_j {

    private String nickname;
    private String message;

    public String getTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
