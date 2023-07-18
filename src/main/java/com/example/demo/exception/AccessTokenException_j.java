package com.example.demo.exception;

import com.google.gson.Gson;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class AccessTokenException_j extends RuntimeException {

    TOKEN_ERROR token_error;

    public enum TOKEN_ERROR {
        UNACCEPT(400, "Token is null or too short."),
        BADTYPE(400, "Token type must be Bearer."),
        MALFORM(400, "Token format is incorrect."),
        BADSIGN(401, "Token's signature is invalid."),
        EXPIRED(403, "Token validity period has expired.");

        private int status;
        private String msg;

        TOKEN_ERROR(int status, String msg) {
            this.status = status;
            this.msg = msg;
        }

        public int getStatus() {
            return this.status;
        }

        public String getMsg() {
            return this.msg;
        }
    }

    public AccessTokenException_j(TOKEN_ERROR error) {
        super(error.name());
        this.token_error = error;
    }

    public void sendResponseError(HttpServletResponse response) {
        response.setStatus(token_error.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Gson gson = new Gson();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", token_error.getMsg());
        resultMap.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        String responseStr = gson.toJson(resultMap);
        try {
            response.getWriter().write(responseStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
