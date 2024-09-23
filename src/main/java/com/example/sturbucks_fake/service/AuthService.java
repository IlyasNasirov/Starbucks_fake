package com.example.sturbucks_fake.service;

import com.example.sturbucks_fake.dto.JwtResponse;
import com.example.sturbucks_fake.dto.SignInUserDto;
import com.example.sturbucks_fake.exception.AuthenticationException;
import com.example.sturbucks_fake.util.JwtTokenUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class AuthService {

    private UserServiceImpl userService;
    private JwtTokenUtils jwtTokenUtils;
    private AuthenticationManager authenticationManager;

    public JwtResponse createAuthToken(SignInUserDto authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Incorrect username or password");
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());

        int id=userService.findByUsername(authRequest.getUsername()).get().getId();
        String token = jwtTokenUtils.generateToken(userDetails);
        return new JwtResponse(id,token);
    }

}
