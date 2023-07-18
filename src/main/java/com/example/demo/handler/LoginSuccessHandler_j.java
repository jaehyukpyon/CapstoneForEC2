package com.example.demo.handler;

import com.example.demo.util.JWTUtil_j;
import com.example.demo.vo.AuthUserVo_j;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
public class LoginSuccessHandler_j implements AuthenticationSuccessHandler {

    private final JWTUtil_j jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        /*
        authentication -> UsernamePasswordAuthenticationToken
        ㄴ principal -> AuthUserVo_j
          ㄴ id
          ㄴ email

        */

        log.info("---------- LoginSuccessHandler_j ----------");

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        log.info(authentication);
        log.info(authentication.getName()); // username

        Map<String, Object> claim = new HashMap<>();
        claim.put("id", ((AuthUserVo_j) authentication.getPrincipal()).getId());
        claim.put("email", authentication.getName());
        claim.put("nickname", ((AuthUserVo_j) authentication.getPrincipal()).getNickname());
        String accessToken = jwtUtil.generateToken(claim, 30);
//        String refreshToken = jwtUtil.generateToken(claim, 30);

        Gson gson = new Gson();
        Map<String, Object> keyMap = new HashMap<>();
        keyMap.put("accessToken", accessToken);
//        keyMap.put("refreshToken", refreshToken);
        keyMap.put("id", ((AuthUserVo_j) authentication.getPrincipal()).getId());
        keyMap.put("email", authentication.getName());
        keyMap.put("nickname", ((AuthUserVo_j) authentication.getPrincipal()).getNickname());
        String jsonStr = gson.toJson(keyMap);

        response.getWriter().write(jsonStr);
    }
}
