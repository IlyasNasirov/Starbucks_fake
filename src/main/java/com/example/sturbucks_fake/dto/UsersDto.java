package com.example.sturbucks_fake.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class UsersDto {

    private List<UserDto> users;

}
