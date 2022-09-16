package com.user.domain.configuration.aop;

import com.user.app.dtos.AccessRequestDTO;
import com.user.domain.configuration.redisConfiguration.RedisRepository;
import com.user.domain.exceptions.BusinessException;
import com.user.domain.service.feign.AuthClient;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Aspect
@Configuration
@Slf4j
public class PreAuthorizedAOP {
    @Resource
    AuthClient authClient;
    @Resource
    RedisRepository redisRepository;

    @Before("@annotation(com.user.domain.configuration.annotation.EnableAuthorized)")
    public void authorizationAllPrivateApi() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        if(!redisRepository.exists((request.getHeader("authorization") != null ? request.getHeader("authorization") : "null" ).replace("Bearer ", ""))){
            throw new BusinessException(String.valueOf(HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED.name());
        }
        boolean authResponse = false;
        try{
            authResponse = authClient.accessCheck(new AccessRequestDTO(request.getRequestURI(), request.getHeader("authorization"), request.getMethod()));
            String logs = "\n" + "=======================================================" + "\n" +
                    "=== " + "Url : " + request.getRequestURL() + "\n" +
                    "=== " + "Method : " + request.getMethod() + "\n" +
                    "=== " + "RequestURI : " + request.getRequestURI() + "\n" +
                    "=== " + "Status code : " + ((authResponse) ? "200" : "401") + "\n" +
                    "=== " + "Time : " + LocalDateTime.now() + "\n" +
                    "=== " + "Data : " + authResponse + "\n" +
                    "======================================================= ";
            log.info(logs);
        }catch (Exception e){
            String logs = "\n" + "=======================================================" + "\n" +
                    "=== " + "Url : " + request.getRequestURL() + "\n" +
                    "=== " + "Method : " + request.getMethod() + "\n" +
                    "=== " + "RequestURI : " + request.getRequestURI() + "\n" +
                    "=== " + "Status code : " + "500" + "\n" +
                    "=== " + "Time : " + LocalDateTime.now() + "\n" +
                    "=== " + "Message : " + "Unauthorized!" + "\n" +
                    "======================================================= ";
            log.info(logs);
            throw new BusinessException(String.valueOf(HttpStatus.UNAUTHORIZED.value()), "Some wrong with Authentication Server!");
        }
        if(!authResponse){
            throw new BusinessException(String.valueOf(HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN.name());
        }
    }
}
