package com.user.app.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.user.app.dtos.AuthUserDTO;
import com.user.domain.configuration.redisConfiguration.RedisRepository;
import com.user.domain.utils.DataUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Resource
    RedisRepository redisRepository;

    @GetMapping
    public Object userInfo(HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String token = httpServletRequest.getHeader("Authorization").replace("Bearer ", "");
        if(!redisRepository.exists(token)){
            return "Unauthorized";
        }else{
            return  DataUtils.entity(redisRepository.get(token).toString(), AuthUserDTO.class);
        }
    }
}
