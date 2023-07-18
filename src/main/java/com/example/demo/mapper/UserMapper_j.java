package com.example.demo.mapper;

import com.example.demo.domain.User_j;
import com.example.demo.dto.UserRegisterDto_j;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper_j {
    Optional<User_j> findByEmail(String email);

    Integer checkEmailDuplication(String email);

    Integer checkNicknameDuplication(String nickname);

    Integer registerUser(UserRegisterDto_j userRegister);
}
