package com.example.sturbucks_fake.exception;

/**
 * Generic 404 RuntimeException Handled by GlobalExceptionHandler...
 */
public abstract class NotFoundException extends RuntimeException {

    public NotFoundException(String name, Integer id) {
        super(String.format("Could not find %s by id=%d", name, id));
    }
}
