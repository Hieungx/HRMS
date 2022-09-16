package com.user.domain.service.impl;

import com.user.domain.common.PageCustom;
import com.user.domain.configuration.mapper.UserMapper;
import com.user.domain.entity.User;
import com.user.domain.repository.UserRepository;
import com.user.domain.service.UserService;
import com.user.domain.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;

    @Resource
    UserMapper userMapper;

    @Resource
    PageUtils pageUtils;

    @Override
    public PageCustom findAllUser(int page, int size) {
        if(page <= 0) page = 1;
        if(size <= 0) size = 10;
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<User> pageUser = userRepository.findAll(pageable);
        PageCustom pageCustom = pageUtils.getPage(userMapper.toListDTO(pageUser.getContent()),pageUser);
        return pageCustom;
    }
}
