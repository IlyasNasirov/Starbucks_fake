package com.example.sturbucks_fake.controller;

import com.example.sturbucks_fake.dto.CategoryDto;
import com.example.sturbucks_fake.dto.DrinkDto;
import com.example.sturbucks_fake.dto.UserDto;
import com.example.sturbucks_fake.service.CategoryService;
import com.example.sturbucks_fake.service.DrinkServiceImpl;
import com.example.sturbucks_fake.service.UserService;
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
@RequestMapping(value = "${baseUrl}/admin", produces = "application/json")
@AllArgsConstructor
@Tag(name = "Admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private UserService userService;
    private DrinkServiceImpl drinkService;
    private CategoryService categoryService;

    @Operation(summary = "Get all users", description = "Returns a list of users")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "List of users",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "Create a new user", description = "Creates a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@Validated @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete user by id", description = "Deletes a user by id. If there is no user with such id, returns 404.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@Parameter(description = "Id of the user") @PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Create new drink", description = "Creates a new drink")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Drink created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DrinkDto.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content),
            @ApiResponse(responseCode = "409", description = "Drink already exists", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping("/drinks")
    public ResponseEntity<DrinkDto> createDrink(@Validated @RequestBody DrinkDto drinkDto) {
        return new ResponseEntity<>(drinkService.createDrink(drinkDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Update drink by id", description = "Updates a drink by id. If there is no drink with such id, returns 404.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Drink updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DrinkDto.class))),
            @ApiResponse(responseCode = "404", description = "Drink not found", content = @Content),
            @ApiResponse(responseCode = "409", description = "Drink already exists", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PatchMapping("/drinks/{id}")
    public ResponseEntity<DrinkDto> updateDrink(@Parameter(description = "Id of the drink") @PathVariable int id, @RequestBody DrinkDto drinkDto) {
        return new ResponseEntity<>(drinkService.updateDrink(id, drinkDto), HttpStatus.OK);
    }

    @Operation(summary = "Delete drink by id", description = "Deletes a drink by id. If there is no drink with such id, returns 404.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Drink deleted"),
            @ApiResponse(responseCode = "404", description = "Drink not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @DeleteMapping("/drinks/{id}")
    public ResponseEntity<Void> deleteDrink(@Parameter(description = "Id of the drink") @PathVariable int id) {
        drinkService.deleteDrink(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get all categories", description = "Returns a list of categories")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of categories",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @Operation(summary = "Delete category by id", description = "Deletes a category by id. If there is no category with such id, returns 404.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Category deleted"),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@Parameter(description = "Id of the category") @PathVariable int id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Create new category", description = "Creates a new category")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Category created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content),
            @ApiResponse(responseCode = "409", description = "Category already exists", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> createCategory(@Validated @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }
}
