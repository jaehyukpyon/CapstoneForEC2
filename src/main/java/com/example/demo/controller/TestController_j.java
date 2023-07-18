package com.example.demo.controller;

import com.example.demo.vo.AuthUserVo_j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test-api-1")
public class TestController_j {

    @GetMapping
    public String testAuthenticationAndPrincipal(Authentication authentication) {
        /*
         * Authentication 객체 안에 principal 맴버 변수 있음.
         * principal 맴버 변수 타입이 AuthUserVo_j.
         * 즉 -> authentication.getPrincipal()을 호출해서 (AuthUserVo_j)로 타입변환 필요
         * AuthUserVo_j는 com.example.demo.vo 패키지 안에 있음
         *
         * 만약 id (PK)를 갖고 오고 싶다 하면 authUser.getId()
         * email을 갖고 오고 싶다 -> authUser.getEmail()
         * nickname을 갖고 오고 싶다 -> authUser.getNickname()을 사용
         * */
        AuthUserVo_j authUser = (AuthUserVo_j) authentication.getPrincipal();
        System.out.println(authUser.getNickname());
        Integer id = authUser.getId();
        return "testAuthenticationAndPrincipal";
    }



    @GetMapping("/test123")
    public String test123(Authentication authentication) {
        AuthUserVo_j authUser = (AuthUserVo_j) authentication.getPrincipal();
        System.out.println(authUser.getNickname());
        System.out.println(authUser.getRegister_date());
        Integer id = authUser.getId();
        return "testAuthenticationAndPrincipal";
    }
}
