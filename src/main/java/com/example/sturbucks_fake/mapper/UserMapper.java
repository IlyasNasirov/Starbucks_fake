package com.example.sturbucks_fake.mapper;

import com.example.sturbucks_fake.dto.UserDto;
import com.example.sturbucks_fake.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "confirmPassword", ignore = true)
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

}
