package com.example.sturbucks_fake.service;

import com.example.sturbucks_fake.dto.CategoriesDto;
import com.example.sturbucks_fake.dto.CategoryDto;
import com.example.sturbucks_fake.dto.DrinkDto;
import com.example.sturbucks_fake.dto.DrinksDto;
import com.example.sturbucks_fake.exception.CategoryNotFoundException;
import com.example.sturbucks_fake.exception.DuplicateEntityException;
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
    public CategoriesDto getAllCategories() {
        return CategoriesDto.builder()
                .categories(CategoryRepository.findAll().stream().map(categoryMapper::toDto).collect(Collectors.toList()))
                .build();
    }

    @Override
    public DrinksDto getAllDrinksByCategory(int categoryId) {
        Category category= CategoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        return DrinksDto.builder()
                .drinks(category.getDrinks().stream().map(drinkMapper::toDto).collect(Collectors.toList()))
                .build();
    }

    public void deleteCategory(int categoryId){
        CategoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        CategoryRepository.deleteById(categoryId);
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        if(CategoryRepository.existsByName(categoryDto.getName())){
            throw new DuplicateEntityException("Category with name "+categoryDto.getName()+" already exists");
        }
        Category category = categoryMapper.toEntity(categoryDto);
        return categoryMapper.toDto(CategoryRepository.save(category));
    }
}