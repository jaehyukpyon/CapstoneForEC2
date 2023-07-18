package com.example.demo.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class LoginFilter_j extends AbstractAuthenticationProcessingFilter  {

    private ObjectMapper objectMapper = new ObjectMapper();

    public LoginFilter_j(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.info("---------- LoginFilter_j ----------");

        if (request.getMethod().equalsIgnoreCase("get")) {
            log.info("---------- GET METHOD IS NOT SUPPORTED ----------");
            Map<String, String> map = new HashMap<>();
            map.put("message", "GET method is not supported for login process. Please use POST method instead.");
            Gson gson = new Gson();
            String result = gson.toJson(map);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
            response.getWriter().write(result);
            return null;
        }

        Map<String, String> jsonData = parseRequestJSON(request);
        log.info("---------- jsonData: " + jsonData + " ----------");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(jsonData.get("email"), jsonData.get("password"));
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    private Map<String, String> parseRequestJSON(HttpServletRequest request) {
        // JSON 데이터를 분석해서 email, password 전달 값을 Map으로 처리
        try (Reader reader = new InputStreamReader(request.getInputStream())) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Map.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
