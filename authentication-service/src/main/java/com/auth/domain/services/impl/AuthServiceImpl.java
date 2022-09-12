package com.auth.domain.services.impl;

import com.auth.app.dtos.AuthUserDTO;
import com.auth.app.dtos.LoginDTO;
import com.auth.domain.configuration.redisConfiguration.RedisRepository;
import com.auth.domain.entities.AuthUser;
import com.auth.domain.exceptions.AuthenticationException;
import com.auth.domain.repositories.AuthUserRepository;
import com.auth.domain.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;

import javax.annotation.Resource;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private AuthUserRepository authUserRepository;

    @Resource
    private RedisRepository redisRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtServiceImpl jwtService;

    @Value("${jwt.expire_token}")
    Long tokenExpiredIn;

    @Value("${jwt.expire_refresh_token}")
    Long refreshTokenExpiredIn;

    @Override
    public AuthUserDTO login(LoginDTO loginDTO, Errors errors) {
        if (errors.hasErrors()) {
            throw new AuthenticationException(String.valueOf(HttpStatus.BAD_REQUEST.value()), errors.getFieldError() != null ? errors.getFieldError().getDefaultMessage() : HttpStatus.BAD_REQUEST.name());
        }
        Boolean canLogin = false;
        AuthUser authUser = null;
        if (!ObjectUtils.isEmpty(loginDTO.getUsername()) && ObjectUtils.isEmpty(loginDTO.getEmail()) && ObjectUtils.isEmpty(loginDTO.getPhone())) {
            authUser = authUserRepository.findByUsername(loginDTO.getUsername());
        }
        if (!ObjectUtils.isEmpty(loginDTO.getEmail()) && ObjectUtils.isEmpty(loginDTO.getUsername()) && ObjectUtils.isEmpty(loginDTO.getPhone())) {
            authUser = authUserRepository.findByEmail(loginDTO.getEmail());
        }
        if (!ObjectUtils.isEmpty(loginDTO.getPhone()) && ObjectUtils.isEmpty(loginDTO.getEmail()) && ObjectUtils.isEmpty(loginDTO.getUsername())) {
            authUser = authUserRepository.findByPhone(loginDTO.getPhone());
        }
        if (ObjectUtils.isEmpty(authUser)) {
            canLogin = false;
        }
        // CHECK PASSWORD
        if (!passwordEncoder.matches(loginDTO.getPassword(), authUser.getPassword())) {
            canLogin = false;
        } else {
            canLogin = true;
        }
        // Gen token and return userInfo
        if (canLogin) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authUser.getUsername(), loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String accessToken = jwtService.generateJwt(authUser.getUsername(), tokenExpiredIn);
            String refreshToken = jwtService.generateJwt(authUser.getUsername(), refreshTokenExpiredIn);
            AuthUserDTO authUserDTO = new AuthUserDTO(authUser.getUserId(), accessToken, refreshToken, authUser.getUsername(), authUser.getFullName(), authUser.getPhone(), authUser.getEmail(), authUser.getRoles());
            // set token to redis
            JSONObject jsonObject = new JSONObject(authUserDTO);
            redisRepository.setObject(accessToken , 60, jsonObject);
            return authUserDTO;
        } else {
            throw new AuthenticationException(String.valueOf(HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED.name());
        }
    }
}


