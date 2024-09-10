package com.example.sturbucks_fake.service;

import com.example.sturbucks_fake.dto.DrinkDto;

import java.util.List;

public interface CategoryService {

    List<DrinkDto> getAllDrinksByCategory(int categoryId);

}
