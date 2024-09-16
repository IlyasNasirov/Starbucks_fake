package com.example.sturbucks_fake.controller;

import com.example.sturbucks_fake.dto.CategoryDto;
import com.example.sturbucks_fake.dto.DrinkDto;
import com.example.sturbucks_fake.dto.UserDto;
import com.example.sturbucks_fake.service.CategoryService;
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
@CrossOrigin(origins = "*")
public class CategoryController {

    private CategoryService service;

    @Operation(summary = "Get all categories", description = "Returns a list of categories")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of categories",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return new ResponseEntity<>(service.getAllCategories(), HttpStatus.OK);
    }

    @Operation(summary = "Get all drinks by category's id", description = "Returns a list of drinks by categoryId")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of drinks",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DrinkDto.class)))),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping("/{id}/drinks")
    public ResponseEntity<List<DrinkDto>> getAllDrinksByCategory(@Parameter(description = "Id of the category") @PathVariable int id) {
        return new ResponseEntity<>(service.getAllDrinksByCategory(id), HttpStatus.OK);
    }


}
