package com.example.sturbucks_fake.service;

import com.example.sturbucks_fake.dto.BucketDto;
import com.example.sturbucks_fake.dto.BucketItemDto;
import com.example.sturbucks_fake.dto.UserDto;
import com.example.sturbucks_fake.model.Bucket;
import com.example.sturbucks_fake.model.BucketItem;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(int userId);

    UserDto createUser(UserDto userDto);

    void deleteUser(int userId);

    UserDto updateUser(int userId, UserDto userDto);

    BucketDto getUserBucket(int userId);

    void addItemToBucket(int userId, int drinkId, int quantity);

}
