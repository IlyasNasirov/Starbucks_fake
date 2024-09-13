package com.example.sturbucks_fake.dto;

import com.example.sturbucks_fake.model.Category;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class DrinkDto {

    private int id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Description cannot be null")
    private String description;

    private Boolean available;

    @Min(value = 1, message = "Price cannot be null")
    private double price;

    @Min(value = 1, message = "Category id cannot be null")
    private int categoryId;

    private String categoryName;

}
