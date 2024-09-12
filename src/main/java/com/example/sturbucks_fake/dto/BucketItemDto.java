package com.example.sturbucks_fake.dto;

import com.example.sturbucks_fake.model.Drink;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BucketItemDto {

    private int quantity;

    private DrinkDto drink;

}
