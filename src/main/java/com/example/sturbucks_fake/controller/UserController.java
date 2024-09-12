package com.example.sturbucks_fake.controller;

import com.example.sturbucks_fake.dto.BucketDto;
import com.example.sturbucks_fake.dto.UserDto;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "${baseUrl}/users", produces = "application/json")
@AllArgsConstructor
@Tag(name = "User")
@CrossOrigin(origins = "*")
public class UserController {

    private UserService service;

    @Operation(summary = "Get all users", description = "Returns a list of users")
    @ApiResponse(responseCode = "200", description = "List of users",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDto.class))))
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @Operation(summary = "Get user by id", description = "Returns a user by id. If there is no user with such id, returns 404.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "user found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@Parameter(description = "Id of the user") @PathVariable int id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @Operation(summary = "Create a new user", description = "Creates a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Validated @RequestBody UserDto userDto) {
        return new ResponseEntity<>(service.createUser(userDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Update user by id", description = "Updates a user by id. If there is no user with such id, returns 404.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Parameter(description = "Id of the user") @PathVariable int id, @RequestBody UserDto userDto) {
        return new ResponseEntity<>(service.updateUser(id, userDto), HttpStatus.OK);
    }

    @Operation(summary = "Delete user by id", description = "Deletes a user by id. If there is no user with such id, returns 404.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@Parameter(description = "Id of the user") @PathVariable int id) {
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get bucket by user's id", description = "Returns a user bucket by id. If there is no user with such id, returns 404.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User bucket found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BucketDto.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/{id}/bucket")
    public ResponseEntity<BucketDto> getUserBucket(@Parameter(description = "Id of the user") @PathVariable int id) {
        return ResponseEntity.ok(service.getUserBucket(id));
    }

    @Operation(summary = "Add item to user's bucket", description = "Adds an item to user's bucket. If there is no user with such id, returns 404.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item added to bucket"),
            @ApiResponse(responseCode = "404", description = "User or Drink not found", content = @Content)
    })
    @PostMapping("/{id}/bucket")
    public ResponseEntity<Void> addItemToBucket(@Parameter(description = "Id of the user") @PathVariable int id,
                                                @Parameter(description = "Id of the drink") @RequestParam int drinkId,
                                                @Parameter(description = "Quantity") @RequestParam int quantity) {
        service.addItemToBucket(id, drinkId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Clear user's bucket", description = "Clears user's bucket. If there is no user with such id, returns 404.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Bucket cleared"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @DeleteMapping("/{id}/bucket")
    public ResponseEntity<Void> clearBucket(@Parameter(description = "Id of the user") @PathVariable int id) {
        service.clearBucket(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Update item in user's bucket", description = "Updates an item in user's bucket. If there is no user or drink with such id, returns 404.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item updated in bucket",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BucketDto.class))),
            @ApiResponse(responseCode = "404", description = "User or Drink not found", content = @Content)
    })
    @PutMapping("/{id}/bucket")
    public ResponseEntity<BucketDto> updateBucket(@Parameter(description = "Id of the user") @PathVariable int id,
                                                  @Parameter(description = "Id of the drink") @RequestParam int drinkId,
                                                  @Parameter(description = "Positive number to increase or negative number to decrease quantity")
                                                  @RequestParam int count) {
        return ResponseEntity.ok(service.updateItemInBucket(id, drinkId, count));
    }

}
