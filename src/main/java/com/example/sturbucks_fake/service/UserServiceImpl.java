package com.example.sturbucks_fake.service;

import com.example.sturbucks_fake.dto.BucketDto;
import com.example.sturbucks_fake.dto.BucketItemDto;
import com.example.sturbucks_fake.dto.UserDto;
import com.example.sturbucks_fake.exception.DrinkNotFoundException;
import com.example.sturbucks_fake.exception.DuplicateEntityException;
import com.example.sturbucks_fake.exception.UserNotFoundException;
import com.example.sturbucks_fake.mapper.BucketItemMapper;
import com.example.sturbucks_fake.mapper.BucketMapper;
import com.example.sturbucks_fake.mapper.UserMapper;
import com.example.sturbucks_fake.model.Bucket;
import com.example.sturbucks_fake.model.BucketItem;
import com.example.sturbucks_fake.model.Drink;
import com.example.sturbucks_fake.model.User;
import com.example.sturbucks_fake.repository.BucketRepository;
import com.example.sturbucks_fake.repository.DrinkRepository;
import com.example.sturbucks_fake.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BucketRepository bucketRepository;
    private DrinkRepository drinkRepository;
    private UserMapper userMapper;
    private BucketMapper bucketMapper;
    private BucketItemMapper bucketItemMapper;

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
        Bucket bucket = new Bucket();
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

    double calculateTotalPrice(Bucket bucket) {

        return bucket.getItems()
                .stream()
                .map(item -> item.getDrink().getPrice() * item.getQuantity())
                .reduce(0.0, Double::sum);
    }

    @Override
    public BucketDto getUserBucket(int userId) {//надо ли здесь вызывать calculateTotalPrice
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        return bucketMapper.toDto(user.getBucket());
    }

    @Override
    public void addItemToBucket(int userId, int drinkId, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Drink drink = drinkRepository.findById(drinkId).orElseThrow(() -> new DrinkNotFoundException(drinkId));
        Bucket bucket = user.getBucket();

        Optional<BucketItem> existingItem = bucket.getItems()
                .stream()
                .filter(item -> item.getDrink().equals(drink))
                .findFirst();

        if (existingItem.isPresent()) {
            if (quantity == 0) {
                bucket.getItems().remove(existingItem.get());
            } else {
                existingItem.get().setQuantity(quantity);
            }
        } else {
            if (quantity > 0) {
                BucketItem newItem = new BucketItem();
                newItem.setDrink(drink);
                newItem.setQuantity(quantity);
                newItem.setBucket(bucket);
                bucket.getItems().add(newItem);
            }
        }
        bucket.setTotalPrice(calculateTotalPrice(bucket));
        bucketRepository.save(bucket);
    }

    @Override
    public BucketDto updateItemInBucket(int userId, int drinkId, int count) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Drink drink = drinkRepository.findById(drinkId).orElseThrow(() -> new DrinkNotFoundException(drinkId));
        Bucket bucket = user.getBucket();

        Optional<BucketItem> existingItem = bucket.getItems()
                .stream()
                .filter(item -> item.getDrink().equals(drink))
                .findFirst();

        if (existingItem.isPresent()) {
            if (count > 0) {
                existingItem.get().setQuantity(existingItem.get().getQuantity() + 1);
            }
            if (count < 0) {
                existingItem.get().setQuantity(existingItem.get().getQuantity() - 1);
            }
            if (existingItem.get().getQuantity() <= 0) {
                bucket.getItems().remove(existingItem.get());
            }
        }
        bucket.setTotalPrice(calculateTotalPrice(bucket));
        bucketRepository.save(bucket);
        return bucketMapper.toDto(bucket);
    }

    @Override
    public void clearBucket(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        Bucket bucket = user.getBucket();
        bucket.getItems().clear();
        bucket.setTotalPrice(calculateTotalPrice(bucket));
        bucketRepository.save(bucket);
    }
}
