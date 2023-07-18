package com.example.demo.service;

import com.example.demo.dto.UserRegisterDto_j;
import com.example.demo.mapper.UserMapper_j;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService_j {

    private final UserMapper_j userMapper;
    private final PasswordEncoder passwordEncoder;

    public Integer checkEmailDuplication(String email) {
        return userMapper.checkEmailDuplication(email.trim());
    }

    public Integer checkUsernicknameDuplication(String nickname) {
        return userMapper.checkNicknameDuplication(nickname.trim());
    }

    public Integer registerUser(UserRegisterDto_j userRegister) {
        userRegister.setPassword(passwordEncoder.encode(userRegister.getPassword()));
        return userMapper.registerUser(userRegister);
    }
}
