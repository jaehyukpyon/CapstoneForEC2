package com.example.demo.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User_j {
    // UserDetailsService_j에서 사용

    private Integer id;
    private String email;
    private String nickname;
    private String password;
    private LocalDateTime registerDate;
    private LocalDateTime updatedDate;
}
