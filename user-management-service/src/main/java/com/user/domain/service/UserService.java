package com.user.domain.service;

import com.user.domain.common.PageCustom;

public interface UserService {

   PageCustom findAllUser(int page, int size);
}
