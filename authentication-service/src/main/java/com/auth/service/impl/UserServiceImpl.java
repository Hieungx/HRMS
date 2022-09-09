package com.auth.service.impl;

import com.auth.entity.AuthUser;
import com.auth.repository.AuthUserRepository;
import com.auth.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collections;


public class UserServiceImpl implements UserService {

    @Resource
    AuthUserRepository authUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = authUserRepository.findByUsername(username);
        if (!ObjectUtils.isEmpty(authUser)) {
            return new User(authUser.getUsername(), authUser.getPassword(), Collections.emptyList());
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
