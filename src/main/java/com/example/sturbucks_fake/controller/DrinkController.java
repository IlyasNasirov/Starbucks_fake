package com.example.sturbucks_fake.controller;

import com.example.sturbucks_fake.dto.DrinkDto;
import com.example.sturbucks_fake.service.DrinkServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/drinks", produces = "application/json")
@AllArgsConstructor
@Tag(name = "Drink")
public class DrinkController {

    private DrinkServiceImpl service;

    @GetMapping
    public ResponseEntity<List<DrinkDto>> getAllDrinks() {
        return ResponseEntity.ok(service.getAllDrinks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrinkDto> getDrinkById(@PathVariable int id) {
        return ResponseEntity.ok(service.getDrinkById(id));
    }

    @PostMapping
    public ResponseEntity<DrinkDto> saveDrink(@Validated @RequestBody DrinkDto drinkDto) {
        return new ResponseEntity<>(service.createDrink(drinkDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DrinkDto> updateDrink(@PathVariable int id, @RequestBody DrinkDto drinkDto) {
        return new ResponseEntity<>(service.updateDrink(id, drinkDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrink(@PathVariable int id) {
        service.deleteDrink(id);
        return ResponseEntity.noContent().build();
    }

}
