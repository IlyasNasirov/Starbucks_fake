package com.example.sturbucks_fake.exception;

/**
 * Exception 404 for Student
 */
public class DrinkNotFoundException extends NotFoundException {

    public DrinkNotFoundException(Integer id) {
        super("drink", id);
    }
}
