package com.example.sturbucks_fake.exception;

/**
 * Exception 404 for Student
 */
public class CategoryNotFoundException extends NotFoundException {

    public CategoryNotFoundException(Integer id) {
        super("category", id);
    }
}
