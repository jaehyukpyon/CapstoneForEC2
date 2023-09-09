package com.example.demo.config;

import com.example.demo.handler.LoginFailureHandler_j;
import com.example.demo.handler.LoginSuccessHandler_j;
import com.example.demo.security.UserDetailsService_j;
import com.example.demo.security.filter.LoginFilter_j;
import com.example.demo.security.filter.TokenCheckFilter_j;
import com.example.demo.util.JWTUtil_j;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration_j {

    private static final String[] NO_AUTHENTICATION_REQUIRED_URIS = {
            "/css/**",
            "/js/**",
            "/images/**",
            "/webjars/**",
            "/favicon.*",
            "/**/favicon.*/**",
            "/*/icon-*",
            "/noauth/**",
            /* Swagger */
            "/swagger/**",
            "/swagger-ui/**",
            "/swagger-ui.*/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
    };

    private final UserDetailsService_j userDetailsService;
    private final JWTUtil_j jwtUtil;
    private final TokenCheckFilter_j tokenCheckFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        log.info("---------- SecurityFilterChain ----------");

        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
        http.authenticationManager(authenticationManager);

        // LoginFilter_j
        LoginFilter_j loginFilter = new LoginFilter_j("/login");
        loginFilter.setAuthenticationManager(authenticationManager);

        LoginSuccessHandler_j loginSuccessHandler = new LoginSuccessHandler_j(jwtUtil);
        loginFilter.setAuthenticationSuccessHandler(loginSuccessHandler);

        LoginFailureHandler_j loginFailureHandler = new LoginFailureHandler_j();
        loginFilter.setAuthenticationFailureHandler(loginFailureHandler);

        // LoginFilter_j 위치 조정하기, 만약 form login을 명시적으로 http로 설정 안 했을 경우에는 UsernamePasswordAuthenticationFilter가 FilterchainProxy에 등록이 안 되는데, 이 경우에는
        // LoginFilter_j LogoutFilter 이후 필터로 등록된다.
        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);

        http.addFilterBefore(
                tokenCheckFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            Gson gson = new Gson();
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("message", "Authentication is required. Access JWT required for authentication is missing.");
            resultMap.put("time", new Date());
            String result = gson.toJson(resultMap);
            response.getWriter().write(result);
        });

        http.formLogin().disable();
        http.httpBasic().disable();
        http.csrf().disable();
        //http.cors().disable();

        http.authorizeRequests().antMatchers(NO_AUTHENTICATION_REQUIRED_URIS).permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}
