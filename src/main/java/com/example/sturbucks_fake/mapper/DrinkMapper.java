package com.example.sturbucks_fake.mapper;

import com.example.sturbucks_fake.dto.DrinkDto;
import com.example.sturbucks_fake.model.Drink;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DrinkMapper {

    @Mapping(target = "category.id", source = "categoryId")
    Drink toEntity(DrinkDto drinkDto);

    @Mapping(target = "categoryId", source = "category.id")
    DrinkDto toDto(Drink drink);

}
