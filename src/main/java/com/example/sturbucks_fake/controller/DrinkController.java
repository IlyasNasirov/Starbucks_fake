package com.example.sturbucks_fake.controller;

import com.example.sturbucks_fake.dto.DrinkDto;
import com.example.sturbucks_fake.dto.UserDto;
import com.example.sturbucks_fake.service.DrinkServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping(value = "/drinks", produces = "application/json")
@AllArgsConstructor
@Tag(name = "Drink")
@CrossOrigin(origins = "*")
public class DrinkController {

    private DrinkServiceImpl service;

    @Operation(summary = "Get all drinks", description = "Returns a list of drinks")
    @ApiResponse(responseCode = "200", description = "List of drinks",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DrinkDto.class))))
    @GetMapping
    public ResponseEntity<List<DrinkDto>> getAllDrinks() {
        return ResponseEntity.ok(service.getAllDrinks());
    }

    @Operation(summary = "Get drink by id", description = "Returns a drink by id. If there is no drink with such id, returns 404.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Drink found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DrinkDto.class))),
            @ApiResponse(responseCode = "404", description = "Drink not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<DrinkDto> getDrinkById(@Parameter(description = "Id of the drink") @PathVariable int id) {
        return ResponseEntity.ok(service.getDrinkById(id));
    }

    @Operation(summary = "Create new drink", description = "Creates a new drink")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Drink created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DrinkDto.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content),
            @ApiResponse(responseCode = "409", description = "Drink already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<DrinkDto> createDrink(@Validated @RequestBody DrinkDto drinkDto) {
        return new ResponseEntity<>(service.createDrink(drinkDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Update drink by id", description = "Updates a drink by id. If there is no drink with such id, returns 404.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Drink updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DrinkDto.class))),
            @ApiResponse(responseCode = "404", description = "Drink not found", content = @Content),
            @ApiResponse(responseCode = "409", description = "Drink already exists", content = @Content)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<DrinkDto> updateDrink(@Parameter(description = "Id of the drink") @PathVariable int id, @RequestBody DrinkDto drinkDto) {
        return new ResponseEntity<>(service.updateDrink(id, drinkDto), HttpStatus.OK);
    }

    @Operation(summary = "Delete drink by id", description = "Deletes a drink by id. If there is no drink with such id, returns 404.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Drink deleted"),
            @ApiResponse(responseCode = "404", description = "Drink not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrink(@Parameter(description = "Id of the drink") @PathVariable int id) {
        service.deleteDrink(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
