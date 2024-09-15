package com.example.sturbucks_fake.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegistrationUserDto {

    @NotNull(message = "firstName cannot be null")
    private String firstName;

    @NotNull(message = "lastName cannot be null")
    private String lastName;

    @NotNull(message = "username cannot be null")
    private String username;

    private String password;

    private String confirmPassword;

}
