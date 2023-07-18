package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserEmailDto_j {

    @NotBlank(message = "Email can't be null, blank or empty space.") // null, "", " " 허용 x
    @Email(message = "Email must be in email format.")
    private String email;
}
