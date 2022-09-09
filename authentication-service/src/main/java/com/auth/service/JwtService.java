package com.auth.service;

public interface JwtService {
    boolean validateToken(String token);
    String getUsernameFromToken(String token);

    String generateJwt(String username, long )
}
