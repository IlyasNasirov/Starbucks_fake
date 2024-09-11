package com.example.sturbucks_fake.service;

import com.example.sturbucks_fake.dto.CategoryDto;
import com.example.sturbucks_fake.dto.DrinkDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    List<DrinkDto> getAllDrinksByCategory(int categoryId);

    void deleteCategory(int categoryId);

    CategoryDto createCategory(CategoryDto categoryDto);
}
