package com.example.demo.controller;

import com.example.demo.response.UserInformationResponse_j;
import com.example.demo.vo.AuthUserVo_j;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Log4j2
@RequiredArgsConstructor
public class UserAuthController_j {

    @PostMapping("/users/get-user-info")
    public ResponseEntity<?> getUserInformation(Authentication authentication) {
        UserInformationResponse_j userDto = new UserInformationResponse_j();
        AuthUserVo_j authUserVo = (AuthUserVo_j) authentication.getPrincipal();
        userDto.setId(authUserVo.getId());
        userDto.setEmail(authUserVo.getEmail());
        userDto.setNickname(authUserVo.getNickname());
        userDto.setRegister_date(authUserVo.getRegister_date());
        userDto.setUpdated_date(authUserVo.getUpdated_date());
        return ResponseEntity.ok(userDto);
        /*
         * "authentication"의 타입은 UserNamePasswordAuthenticationToken
         * "authentication" 안에 맴버변수로 "principal"이 있는데 타입은 AuthUserVo_j
         * "principal"안에 맴버변수로 id, email, nickname, password(암호화된), register_date, update_date, username(이메일), authorities(Collections$UnmodifiableSet -> 이 컬랙션 안에 각각의 요소 타입은 SimpleGrantedAuthority("ROLE_USER")*/
    }
}
