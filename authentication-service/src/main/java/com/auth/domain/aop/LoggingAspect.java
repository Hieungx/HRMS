package com.auth.domain.aop;

import com.auth.domain.exceptions.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Aspect
@Configuration
@Slf4j
public class LoggingAspect {

    // TODO : POINT_CUT
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void controller() {}

    @Around(value = "controller()")
    public Object controller(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        String logs = "\n" + "=======================================================" + "\n" +
                "=== " + "Url : " + request.getRequestURL() + "\n" +
                "=== " + "Method : " + request.getMethod() + "\n" +
                "=== " + "RequestURI : " + request.getRequestURI() + "\n" +
                "=== " + "Status code : " + response.getStatus() + "\n" +
                "=== " + "Time : " + LocalDateTime.now() + "\n" +
                "======================================================= ";
        log.info(logs);
        return result;
    }


    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {}

    @AfterThrowing(value="execution(* com.auth.domain.services.impl.AuthServiceImpl.*(..))",throwing="ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, AuthenticationException ex) {

        System.out.println("After Throwing exception in method : "+joinPoint.getSignature());
        System.out.println("Exception is : "+ex.getSuperMessage());
        String logs = "\n" + "=======================================================" + "\n" +
                "=== " + "After Throwing exception in method  : " + joinPoint.getSignature() + "\n" +
                "=== " + "Error message : " + ex.getSuperMessage() + "\n" +
                "=== " + "Time : " + LocalDateTime.now() + "\n" +
                "======================================================= ";
        log.info(logs);
    }
}
