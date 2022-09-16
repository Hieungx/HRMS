package com.user.app.controllers;

import com.user.app.dtos.UserDTO;
import com.user.app.responses.BusinessResponse;
import com.user.domain.configuration.annotation.EnableAuthorized;
import com.user.domain.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Resource
    UserService userService;

    @GetMapping(value = "/all")
    @Cacheable(value="caching", key = "{#page, #size}")
    @EnableAuthorized
    public BusinessResponse<Page<UserDTO>> getAllUser(@RequestParam int page, @RequestParam int size){
        return BusinessResponse.ok(userService.findAllUser(page, size));
    }
}
