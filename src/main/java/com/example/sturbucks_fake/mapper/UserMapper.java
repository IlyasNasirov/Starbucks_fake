package com.example.sturbucks_fake.mapper;

import com.example.sturbucks_fake.dto.UserDto;
import com.example.sturbucks_fake.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserDto toDto(User user);

    User toEntity(UserDto userDto);

}
