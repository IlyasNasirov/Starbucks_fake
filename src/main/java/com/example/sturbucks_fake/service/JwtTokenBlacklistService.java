package com.example.sturbucks_fake.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service

public class JwtTokenBlacklistService {

    private Set<String> blacklistedTokens = new HashSet<>();

    public void addToBlacklist(String token) {
        blacklistedTokens.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }

}
