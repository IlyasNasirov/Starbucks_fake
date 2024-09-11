package com.example.sturbucks_fake.service;

import com.example.sturbucks_fake.dto.BucketDto;
import com.example.sturbucks_fake.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(int userId);

    UserDto createUser(UserDto userDto);

    void deleteUser(int userId);

    UserDto updateUser(int userId, UserDto userDto);

    BucketDto getBucket(int userId);


}
