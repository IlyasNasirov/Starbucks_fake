package com.example.sturbucks_fake.service;

import com.example.sturbucks_fake.dto.DrinkDto;
import com.example.sturbucks_fake.dto.DrinksDto;

import java.util.List;

public interface DrinkService {

    DrinksDto getAllDrinks();

    DrinkDto getDrinkById(int drinkId);

    DrinkDto createDrink(DrinkDto drinkDto);

    void deleteDrink(int drinkId);

    DrinkDto updateDrink(int drinkId, DrinkDto drinkDto);

}
