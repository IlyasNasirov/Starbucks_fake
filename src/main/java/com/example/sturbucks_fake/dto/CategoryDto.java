package com.example.sturbucks_fake.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CategoryDto {

    private int id;

    @NotNull(message = "Name cannot be null")
    private String name;

}
