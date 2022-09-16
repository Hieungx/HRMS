package com.user.domain.exceptions;

import com.user.app.responses.BusinessResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
public class HandleException {

    @ExceptionHandler(BusinessException.class)
    public BusinessResponse handleAuthenticationException(BusinessException ex) {
        return BusinessResponse.error(ex.getExceptionCode(), ex.getExceptionMessage());
    }
}