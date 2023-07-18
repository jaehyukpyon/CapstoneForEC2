package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserNicknameDto_j {

    @NotBlank(message = "Nickname can't be null, blank or empty space.") // null, "", " " 허용 x
    @Pattern(regexp = "^[A-Za-z0-9ㄱ-ㅎㅏ-ㅣ가-힣]{2,10}$", message = "닉네임은 한글, 영어, 숫자만 가능. 최소 두 글자 최대 열 글자. (특수문자 및 공백 불가)")
    private String nickname;
}
