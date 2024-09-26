package com.example.sturbucks_fake.service;

import com.example.sturbucks_fake.dto.CategoriesDto;
import com.example.sturbucks_fake.dto.CategoryDto;
import com.example.sturbucks_fake.dto.DrinkDto;
import com.example.sturbucks_fake.dto.DrinksDto;

import java.util.List;

public interface CategoryService {

    CategoriesDto getAllCategories();

    DrinksDto getAllDrinksByCategory(int categoryId);

    void deleteCategory(int categoryId);

    CategoryDto createCategory(CategoryDto categoryDto);
}
