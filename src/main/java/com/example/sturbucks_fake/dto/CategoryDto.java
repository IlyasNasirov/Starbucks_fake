package com.example.sturbucks_fake.dto;

import javax.validation.constraints.NotNull;

public class CategoryDto {

    @NotNull(message = "name cannot be null")
    private String name;



}
