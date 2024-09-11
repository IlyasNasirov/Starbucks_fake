package com.example.sturbucks_fake.exception;

public class UserNotFoundException extends NotFoundException{

    public UserNotFoundException(Integer id) {
        super("user", id);
    }
}
