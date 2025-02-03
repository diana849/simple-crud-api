package com.example.simplecrudapi.configuration;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.simplecrudapi.service..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        final var className = joinPoint.getSignature().getDeclaringTypeName();
        final var methodName = joinPoint.getSignature().getName();
        final var args = joinPoint.getArgs();

        log.info("Executing {}.{}() with arguments {}", className, methodName, args);
    }

    @After("execution(* com.example.simplecrudapi.service..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("Completed method {}", joinPoint.getSignature().getName());
    }
}