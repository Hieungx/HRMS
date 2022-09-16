package com.auth.app.controllers;

import com.auth.app.dtos.AccessRequestDTO;
import com.auth.app.dtos.LoginDTO;
import com.auth.app.responses.AuthResponse;
import com.auth.domain.configuration.redisConfiguration.RedisRepository;
import com.auth.domain.configuration.webSecurityConfiguration.AuthorizationConfig;
import com.auth.domain.services.AuthService;
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
    RedisRepository redisRepository;
    @Resource
    AuthorizationConfig authorizationConfig;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AuthResponse loginController(@Valid @RequestBody LoginDTO loginDTO, Errors errors) {
        return new AuthResponse(String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.name(), authService.login(loginDTO, errors));
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public AuthResponse logout(HttpServletRequest request) {
        if (!redisRepository.exists(request.getHeader("Authorization").replace("Bearer ", ""))) {
            return new AuthResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), "You must login first", null);
        } else {
            redisRepository.delete(request.getHeader("Authorization").replace("Bearer ", ""));
            return new AuthResponse(String.valueOf(HttpStatus.OK.value()), "Goodbye", null);
        }
    }
    @PostMapping(value = "/access-request")
    public boolean accessRequest(@RequestBody AccessRequestDTO accessRequestDTO) {
        return authorizationConfig.authorizationAllPrivateApi(accessRequestDTO);
    }
}
