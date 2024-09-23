package com.example.sturbucks_fake.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

    private int id;

    private String token;

}
