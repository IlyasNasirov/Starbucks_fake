package com.example.sturbucks_fake.service;

import com.example.sturbucks_fake.dto.DrinkDto;
import com.example.sturbucks_fake.exception.CategoryNotFoundException;
import com.example.sturbucks_fake.mapper.CategoryMapper;
import com.example.sturbucks_fake.mapper.DrinkMapper;
import com.example.sturbucks_fake.model.Category;
import com.example.sturbucks_fake.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository CategoryRepository;
    private CategoryMapper categoryMapper;
    private DrinkMapper drinkMapper;

    @Override
    public List<DrinkDto> getAllDrinksByCategory(int categoryId) {
        Category category= CategoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        return category.getDrinks().stream().map(drinkMapper::toDto).collect(Collectors.toList());
    }

}
