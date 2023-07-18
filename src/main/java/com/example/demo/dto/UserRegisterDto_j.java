package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegisterDto_j {

    private String email;
    private String nickname;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$])(?=.*[0-9])[A-Za-z\\d!@#$]{8,16}$",
            message = "비밀번호는 8자리 이상 16자리 이하, 영어 대소문자 각각 최소 한 개 이상, 숫자 최소 한 개 이상, 특수기호 중 !, @, #, $ 최소 한 개 이상을 포함해야 합니다.(나머지 특수문자, 공백, 한글 불가)")
    private String password;
}
