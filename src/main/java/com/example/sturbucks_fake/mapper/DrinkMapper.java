package com.example.sturbucks_fake.mapper;

import com.example.sturbucks_fake.dto.DrinkDto;
import com.example.sturbucks_fake.model.Drink;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DrinkMapper {

    Drink toEntity(DrinkDto drinkDto);

    DrinkDto toDto(Drink drink);

}
