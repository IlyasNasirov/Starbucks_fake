package com.example.sturbucks_fake.controller;

import com.example.sturbucks_fake.dto.JwtResponse;
import com.example.sturbucks_fake.dto.SignInUserDto;
import com.example.sturbucks_fake.dto.UserDto;
import com.example.sturbucks_fake.service.AuthService;
import com.example.sturbucks_fake.service.JwtTokenBlacklistService;
import com.example.sturbucks_fake.service.UserService;
import com.example.sturbucks_fake.util.JwtTokenUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("${baseUrl}")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private UserService userService;
    private AuthService authService;
    private JwtTokenBlacklistService jwtTokenBlacklistService;
    private JwtTokenUtils jwtTokenUtils;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody SignInUserDto authRequest) {
        String jwt = authService.createAuthToken(authRequest).getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt);

        return ResponseEntity.ok().headers(headers).build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = jwtTokenUtils.getTokenFromRequest(request);
        jwtTokenBlacklistService.addToBlacklist(token);

        return ResponseEntity.ok().build();
    }

}
