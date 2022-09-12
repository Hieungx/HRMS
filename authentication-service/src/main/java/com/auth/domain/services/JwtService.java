package com.auth.domain.services;

import com.auth.app.dtos.AuthUserDTO;

public interface JwtService {
    boolean validateToken(String token);

    AuthUserDTO getUserInfoFromToken(String token);

    String generateJwt(String username, long expireTime);
}
