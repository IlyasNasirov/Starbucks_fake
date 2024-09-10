package com.example.sturbucks_fake.controller;

import com.example.sturbucks_fake.dto.CategoryDto;
import com.example.sturbucks_fake.dto.DrinkDto;
import com.example.sturbucks_fake.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "${baseUrl}/categories", produces = "application/json")
@AllArgsConstructor
@Tag(name = "Category")
public class CategoryController {

    private CategoryService service;

    @GetMapping("/{id}/drinks")
    public ResponseEntity<List<DrinkDto>> getAllDrinksByCategory(@PathVariable int id){
        return new ResponseEntity<>(service.getAllDrinksByCategory(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        service.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Validated @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(service.createCategory(categoryDto), HttpStatus.CREATED);
    }

}
