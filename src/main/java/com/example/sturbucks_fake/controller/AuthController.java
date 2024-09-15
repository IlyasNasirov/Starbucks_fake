package com.example.sturbucks_fake.controller;

import com.example.sturbucks_fake.dto.RegistrationUserDto;
import com.example.sturbucks_fake.dto.SignInUserDto;
import com.example.sturbucks_fake.dto.UserDto;
import com.example.sturbucks_fake.service.AuthService;
import com.example.sturbucks_fake.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class AuthController {

    private UserServiceImpl userService;
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody SignInUserDto authRequest) {
        return ResponseEntity.ok(authService.createAuthToken(authRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationUserDto registrationUserDto) {
        UserDto userDto = userService.createNewUser(registrationUserDto);
        return ResponseEntity.ok(userDto);
    }

}
