package com.example.sturbucks_fake.controller;

import com.example.sturbucks_fake.dto.SignInUserDto;
import com.example.sturbucks_fake.dto.UserDto;
import com.example.sturbucks_fake.service.AuthService;
import com.example.sturbucks_fake.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${baseUrl}")
@AllArgsConstructor
public class AuthController {

    private UserService userService;
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody SignInUserDto authRequest) {
        return ResponseEntity.ok(authService.createAuthToken(authRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

}
