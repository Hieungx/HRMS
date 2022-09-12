package com.auth.domain.exceptions;

import com.auth.app.responses.AuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
public class HandleException {

    @ExceptionHandler(AuthenticationException.class)
    public AuthResponse handleAuthenticationException(AuthenticationException ex) {
        return AuthResponse.error(ex.getExceptionCode(), ex.getExceptionMessage());
    }
}
