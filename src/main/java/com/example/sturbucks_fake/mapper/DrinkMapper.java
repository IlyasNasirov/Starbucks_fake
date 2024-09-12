package com.example.sturbucks_fake.mapper;

import com.example.sturbucks_fake.dto.DrinkDto;
import com.example.sturbucks_fake.model.Drink;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DrinkMapper {

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    DrinkDto toDto(Drink drink);

    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "category.name", source = "categoryName")
    Drink toEntity(DrinkDto drinkDto);

}