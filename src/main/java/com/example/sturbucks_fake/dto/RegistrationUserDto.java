package com.example.sturbucks_fake.dto;

import lombok.Data;

@Data
public class RegistrationUserDto {

    private String username;

    private String password;

    private String confirmPassword;

}
