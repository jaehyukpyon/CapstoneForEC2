package com.example.demo.response;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserEmailResponse_j {

    private String email;
    private String message;

    public String getTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
