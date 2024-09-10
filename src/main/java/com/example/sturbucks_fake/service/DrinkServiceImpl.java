package com.example.sturbucks_fake.service;

import com.example.sturbucks_fake.dto.DrinkDto;
import com.example.sturbucks_fake.exception.CategoryNotFoundException;
import com.example.sturbucks_fake.exception.DrinkNotFoundException;
import com.example.sturbucks_fake.exception.DuplicateEntityException;
import com.example.sturbucks_fake.mapper.DrinkMapper;
import com.example.sturbucks_fake.model.Category;
import com.example.sturbucks_fake.model.Drink;
import com.example.sturbucks_fake.repository.CategoryRepository;
import com.example.sturbucks_fake.repository.DrinkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private DrinkRepository drinkRepository;
    private CategoryRepository categoryRepository;
    private DrinkMapper drinkMapper;

    @Override
    public List<DrinkDto> getAllDrinks() {
        return drinkRepository.findAll().stream().map(drinkMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public DrinkDto getDrinkById(int drinkId) {
        Drink drink = drinkRepository.findById(drinkId).orElseThrow(() -> new DrinkNotFoundException(drinkId));
        return drinkMapper.toDto(drink);
    }

    @Override
    public DrinkDto createDrink(DrinkDto drinkDto) {
        categoryRepository.findById(drinkDto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(drinkDto.getCategoryId()));
        if(drinkRepository.existsByName(drinkDto.getName())){
            throw new DuplicateEntityException("Drink with this name already exists ");
        }
        Drink drink = drinkMapper.toEntity(drinkDto);
        return drinkMapper.toDto(drinkRepository.save(drink));
    }

    @Override
    public void deleteDrink(int drinkId) {
        if (!drinkRepository.existsById(drinkId)) {
            throw new DrinkNotFoundException(drinkId);
        }
        drinkRepository.deleteById(drinkId);
    }

    @Override
    public DrinkDto updateDrink(int drinkId, DrinkDto drinkDto) {
        Drink drink = drinkRepository.findById(drinkId).orElseThrow(() -> new DrinkNotFoundException(drinkId));
        if (drinkDto.getName() != null) {
            drink.setName(drinkDto.getName());
        }
        if (drinkDto.getDescription() != null) {
            drink.setDescription(drinkDto.getDescription());
        }
        drink.setAvailable(drinkDto.isAvailable());
        return drinkMapper.toDto(drinkRepository.save(drink));
    }

    @Override
    public void deleteAllDrinks() {

    }
}
