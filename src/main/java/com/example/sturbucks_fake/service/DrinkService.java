package com.example.sturbucks_fake.service;

import com.example.sturbucks_fake.dto.DrinkDto;

import java.util.List;

public interface DrinkService {

    List<DrinkDto> getAllDrinks();

    DrinkDto getDrinkById(int drinkId);

    DrinkDto createDrink(DrinkDto drinkDto);

    void deleteDrink(int drinkId);

    DrinkDto updateDrink(int drinkId, DrinkDto drinkDto);

    void deleteAllDrinks();

}
