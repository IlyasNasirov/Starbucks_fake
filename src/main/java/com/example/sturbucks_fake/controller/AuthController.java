package com.example.sturbucks_fake.controller;

import com.example.sturbucks_fake.dto.JwtResponse;
import com.example.sturbucks_fake.dto.SignInUserDto;
import com.example.sturbucks_fake.dto.UserDto;
import com.example.sturbucks_fake.service.AuthService;
import com.example.sturbucks_fake.service.JwtTokenBlacklistService;
import com.example.sturbucks_fake.service.UserService;
import com.example.sturbucks_fake.util.JwtTokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("${baseUrl}")
@AllArgsConstructor
@Tag(name = "Authorization")
@CrossOrigin(origins = "*")
public class AuthController {

    private UserService userService;
    private AuthService authService;
    private JwtTokenBlacklistService jwtTokenBlacklistService;
    private JwtTokenUtils jwtTokenUtils;

    @Operation(summary = "Generate JWT token",
            description = "User login and generation of JWT token for authentication.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful login",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtResponse.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials",
                    content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@Parameter(description = "User credentials") @RequestBody SignInUserDto authRequest) {
        String jwt = authService.createAuthToken(authRequest).getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt);

        return ResponseEntity.ok().headers(headers).build();
    }

    @Operation(summary = "Register a new user",
            description = "Registers a new user and returns the created user details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully registered",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Username already exists",
                    content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @Operation(summary = "Logout user",
            description = "Logs out the user by invalidating the current JWT by adding it to the blacklist.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully logged out"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = jwtTokenUtils.getTokenFromRequest(request);
        jwtTokenBlacklistService.addToBlacklist(token);

        return ResponseEntity.ok().build();
    }

}
