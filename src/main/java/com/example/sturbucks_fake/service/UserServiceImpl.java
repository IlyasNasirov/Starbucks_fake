package com.example.sturbucks_fake.service;

import com.example.sturbucks_fake.dto.BucketDto;
import com.example.sturbucks_fake.dto.UserDto;
import com.example.sturbucks_fake.exception.DuplicateEntityException;
import com.example.sturbucks_fake.exception.UserNotFoundException;
import com.example.sturbucks_fake.mapper.BucketMapper;
import com.example.sturbucks_fake.mapper.UserMapper;
import com.example.sturbucks_fake.model.Bucket;
import com.example.sturbucks_fake.model.User;
import com.example.sturbucks_fake.repository.BucketRepository;
import com.example.sturbucks_fake.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BucketRepository bucketRepository;
    private UserMapper userMapper;
    private BucketMapper bucketMapper;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new DuplicateEntityException("Username already exists");
        }
        Bucket bucket=new Bucket();
        user.setBucket(bucket);
        bucket.setUser(user);

        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto updateUser(int userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        if (userDto.getFirstName() != null) {
            user.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName() != null) {
            user.setLastName(userDto.getLastName());
        }
        if (userDto.getUsername() != null) {
            if (userRepository.existsByUsername(userDto.getUsername())) {
                throw new DuplicateEntityException("Username already exists");
            }
            user.setUsername(userDto.getUsername());
        }
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public BucketDto getBucket(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        return bucketMapper.toDto(user.getBucket());
    }
}
