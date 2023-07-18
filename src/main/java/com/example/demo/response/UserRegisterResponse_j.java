package com.example.demo.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterResponse_j {

    private String email;
    private String message;

    public UserRegisterResponse_j(String email, String message) {
        this.email = email;
        this.message = message;
    }
}
