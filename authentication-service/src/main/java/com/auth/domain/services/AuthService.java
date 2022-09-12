package com.auth.domain.services;

import com.auth.app.dtos.AuthUserDTO;
import com.auth.app.dtos.LoginDTO;
import org.springframework.validation.Errors;

public interface AuthService {
    AuthUserDTO login(LoginDTO loginDTO, Errors errors);
}
