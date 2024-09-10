package com.example.sturbucks_fake.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class DrinkDto {

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Description cannot be null")
    private String description;

    @AssertTrue(message = "The drink must be available.")
    private boolean available;

}
