package com.auth.service.impl;

import com.auth.service.JwtService;

public class JwtServiceImpl implements JwtService {
    @Override
    public boolean validateToken(String token) {
        return false;
    }

    @Override
    public String getUsernameFromToken(String token) {
        return null;
    }
}
