package com.example.demo.security.filter;

import com.example.demo.exception.AccessTokenException_j;
import com.example.demo.security.UserDetailsService_j;
import com.example.demo.util.JWTUtil_j;
import com.example.demo.vo.AuthUserVo_j;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class TokenCheckFilter_j extends OncePerRequestFilter {

    @Autowired
    private JWTUtil_j jwtUtil;

    @Autowired
    private UserDetailsService_j userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        if (!path.startsWith("/api")) {
            filterChain.doFilter(request, response);
            return;
        }
        log.info("---------- TokenCheckFilter_j - doFilterInternal ----------");
        log.info("JWTUtil: " + jwtUtil);

        try {
            Map<String, Object> stringObjectMap = validateAccessToken(request);
            String email = (String) stringObjectMap.get("email");
            AuthUserVo_j userDetails;

            try {
                userDetails = (AuthUserVo_j) userDetailsService.loadUserByUsername(email);
            } catch (UsernameNotFoundException exception) {
                Gson gson = new Gson();

                Map<String, String> resultMap = new HashMap<>();
                resultMap.put("message", "AccessToken 자체는 유효하나, 사용자 이메일: '" + email + "'에 해당하는 사용자가 DB에 존재하지 않습니다.");
                resultMap.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

                String responseStr = gson.toJson(resultMap);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setStatus(HttpStatus.NOT_FOUND.value());
                response.setCharacterEncoding("UTF-8");
                try {
                    response.getWriter().write(responseStr);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }

            UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

            SecurityContextHolder.clearContext();
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authResult);
            SecurityContextHolder.setContext(context);
            filterChain.doFilter(request, response);
        } catch (AccessTokenException_j accessTokenException) {
            accessTokenException.sendResponseError(response);
        }
    }

    private Map<String, Object> validateAccessToken(HttpServletRequest request) throws AccessTokenException_j {
        String headerStr = request.getHeader("Authorization");

        if (headerStr == null || headerStr.length() < 8) {
            throw new AccessTokenException_j(AccessTokenException_j.TOKEN_ERROR.UNACCEPT); // null or too short
        }

        String tokenType = headerStr.substring(0, 6);
        String tokenStr = headerStr.substring(7);

        if (tokenType.equalsIgnoreCase("Bearer") == false) {
            throw new AccessTokenException_j(AccessTokenException_j.TOKEN_ERROR.BADTYPE); // must be Bearer
        }

        try {
            Map<String, Object> values = jwtUtil.validateToken(tokenStr);
            return values;
        } catch (MalformedJwtException malformedJwtException) {
            log.error("---------- MalformedJwtException ----------");
            throw new AccessTokenException_j(AccessTokenException_j.TOKEN_ERROR.MALFORM);
        } catch (SignatureException signatureException) {
            log.error("---------- SignatureException ----------");
            throw new AccessTokenException_j(AccessTokenException_j.TOKEN_ERROR.BADSIGN);
        } catch (ExpiredJwtException expiredJwtException) {
            log.error("---------- ExpiredJwtException ----------");
            throw new AccessTokenException_j(AccessTokenException_j.TOKEN_ERROR.EXPIRED);
        }
    }
}
