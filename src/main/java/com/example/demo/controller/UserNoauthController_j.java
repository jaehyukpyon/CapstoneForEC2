package com.example.demo.controller;

import com.example.demo.dto.UserEmailDto_j;
import com.example.demo.dto.UserNicknameDto_j;
import com.example.demo.dto.UserRegisterDto_j;
import com.example.demo.response.UserEmailResponse_j;
import com.example.demo.response.UserNicknameResponse_j;
import com.example.demo.response.UserRegisterResponse_j;
import com.example.demo.service.UserService_j;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/noauth")
@RequiredArgsConstructor
public class UserNoauthController_j {

    private final UserService_j userService;

    @PostMapping("/users/email-duplication")
    public ResponseEntity<UserEmailResponse_j> emailDuplicateCheck(@Validated @RequestBody UserEmailDto_j userEmailDto) {

        Integer count = userService.checkEmailDuplication(userEmailDto.getEmail());

        if (count > 0) {
            UserEmailResponse_j body = new UserEmailResponse_j(userEmailDto.getEmail(), "이 이메일은 이미 존재햐여 사용할 수 없습니다.");
            ResponseEntity<UserEmailResponse_j> result = new ResponseEntity<>(body, HttpStatus.CONFLICT);
            return result;
        } else {
            return ResponseEntity.ok().body(new UserEmailResponse_j(userEmailDto.getEmail(), "이 이메일을 사용할 수 있습니다."));
        }
    }

    @PostMapping("/users/nickname-duplication")
    public ResponseEntity<UserNicknameResponse_j> nicknameDuplicateCheck(@Validated @RequestBody UserNicknameDto_j userNicknameDto) {
        Integer count = userService.checkUsernicknameDuplication(userNicknameDto.getNickname());

        if (count > 0) {
            UserNicknameResponse_j body = new UserNicknameResponse_j(userNicknameDto.getNickname(), "이 닉네임은 이미 존재하여 사용할 수 없습니다.");
            ResponseEntity<UserNicknameResponse_j> result = new ResponseEntity<>(body, HttpStatus.CONFLICT);
            return result;
        } else {
            return ResponseEntity.ok().body(new UserNicknameResponse_j(userNicknameDto.getNickname(), "이 닉네임을 사용할 수 있습니다."));
        }
    }

    @PostMapping("/users/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody UserRegisterDto_j userRegister) {
        Integer count = userService.registerUser(userRegister);

        if (count == 1) {
            return new ResponseEntity<UserRegisterResponse_j>(new UserRegisterResponse_j(userRegister.getEmail(), "회원가입이 정상적으로 완료되었습니다."), HttpStatus.CREATED);
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("message", "회원가입 중 오류가 발생하였습니다.");
            Gson gson = new Gson();
            String toJson = gson.toJson(map);
            return ResponseEntity.internalServerError().body(toJson);
        }
    }
}
