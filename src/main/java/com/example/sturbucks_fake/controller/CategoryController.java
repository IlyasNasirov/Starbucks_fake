package com.example.sturbucks_fake.controller;

import com.example.sturbucks_fake.dto.DrinkDto;
import com.example.sturbucks_fake.service.CategoryServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/categories", produces = "application/json")
@AllArgsConstructor
@Tag(name = "Category")
public class CategoryController {

    private CategoryServiceImpl service;

    @GetMapping("/{id}/drinks")
    public ResponseEntity<List<DrinkDto>> getAllDrinksByCategory(@PathVariable int id){
        return new ResponseEntity<>(service.getAllDrinksByCategory(id), HttpStatus.OK);
    }

}
