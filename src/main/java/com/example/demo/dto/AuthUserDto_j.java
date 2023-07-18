package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@ToString
public class AuthUserDto_j extends User {

    private Integer id;
    private String email;
    private String nickname;
    private String password;
    private LocalDateTime register_date;
    private LocalDateTime updated_date;

    public AuthUserDto_j(Integer id, String email, String nickname, String password, LocalDateTime register_date, LocalDateTime updated_date, Collection<GrantedAuthority> authorities) {
        super(email, password, authorities);
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.register_date = register_date;
        this.updated_date = updated_date;
    }
}
