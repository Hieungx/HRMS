package com.auth.domain.services.impl;

import com.auth.domain.repositories.AuthUserRepository;
import com.auth.domain.services.UserService;
import com.auth.domain.entities.AuthUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collections;

@Service
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
