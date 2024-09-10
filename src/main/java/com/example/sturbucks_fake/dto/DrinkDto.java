package com.example.sturbucks_fake.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class DrinkDto {

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Description cannot be null")
    private String description;

    private Boolean available;

    @Min(value = 1, message = "Price cannot be null")
    private long price;

    @Min(value = 1, message = "Category id cannot be null")
    private int categoryId;

}
