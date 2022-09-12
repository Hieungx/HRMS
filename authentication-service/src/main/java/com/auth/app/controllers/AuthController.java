package com.auth.app.controllers;

import com.auth.app.dtos.AuthUserDTO;
import com.auth.app.dtos.LoginDTO;
import com.auth.app.responses.AuthResponse;
import com.auth.domain.configuration.redisConfiguration.RedisRepository;
import com.auth.domain.services.AuthService;
import com.auth.domain.services.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/auth")
@Slf4j
public class AuthController {

    @Resource
    AuthService authService;

    @Resource
    JwtService jwtService;

    @Resource
    RedisRepository redisRepository;

    @PostMapping(value = "/login")
    public AuthResponse login(@Valid @RequestBody LoginDTO loginDTO, Errors errors) {
        return new AuthResponse(String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.name(), authService.login(loginDTO, errors));
    }

    @GetMapping(value = "/logout")
    public AuthResponse logout(HttpServletRequest request) {
        redisRepository.delete(request.getHeader("Authorization").replace("Bearer ", ""));
        return new AuthResponse(String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.name(),"Goodbye");
    }

    @GetMapping(value = "/user-info")
    public AuthUserDTO getUserInfo (HttpServletRequest request){
        return jwtService.getUserInfoFromToken(request.getHeader("Authorization"));
    }
}
