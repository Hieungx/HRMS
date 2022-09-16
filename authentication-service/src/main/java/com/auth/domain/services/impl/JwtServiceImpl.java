package com.auth.domain.services.impl;

import com.auth.app.dtos.AuthUserDTO;
import com.auth.domain.entities.AuthUser;
import com.auth.domain.exceptions.AuthenticationException;
import com.auth.domain.repositories.AuthUserRepository;
import com.auth.domain.services.AuthService;
import com.auth.domain.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService {


    @Value("${jwt.secret_key}")
    String secretKey;
    @Value("${jwt.prefix_token}")
    String prefixToken;

    @Resource
    AuthUserRepository authUserRepository;

    @Override
    public boolean validateToken(String token) {
        if (!token.startsWith(prefixToken)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
    }

    @Override
    public AuthUserDTO getUserInfoFromToken(String token){
        try {
            if (ObjectUtils.isEmpty(token)) {
                return null;
            }
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token.replace(prefixToken, "")).getBody();
            AuthUser authUser = authUserRepository.findByUsername(claims.get("username", String.class));
            return new AuthUserDTO(authUser.getUserId(), authUser.getUsername(), authUser.getFullName(), authUser.getPhone(), authUser.getEmail(), authUser.getRoles());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
            throw new AuthenticationException(String.valueOf(HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED.name(), ex.getMessage());
        }
    }
    @Override
    public String generateJwt(String username, long expireTime) {
        Map<String ,Object> claims = new HashMap<>();
        claims.put("username", username);
        return Jwts.builder()
                .setSubject(username)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }
}
