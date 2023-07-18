package com.example.demo.security;

import com.example.demo.domain.User_j;
import com.example.demo.mapper.UserMapper_j;
import com.example.demo.vo.AuthUserVo_j;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserDetailsService_j implements UserDetailsService {

    private final UserMapper_j userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User_j> result = userMapper.findByEmail(email);
        User_j user = result.orElseThrow(() -> new UsernameNotFoundException("이메일: '" + email + "' 에 해당하는 사용자를 찾을 수 없습니다."));
        log.info("로그인 시도 중인 사용자 확인: " + user.toString());

        log.info("---------- UserDetailsService_j - loadUserByUsername ----------");

        AuthUserVo_j authUser = new AuthUserVo_j(
                user.getId(),
                user.getEmail(),
                user.getNickname(),
                user.getPassword(),
                user.getRegisterDate(),
                user.getUpdatedDate(),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
        );
        log.info(authUser);
        return authUser;
    }
}
