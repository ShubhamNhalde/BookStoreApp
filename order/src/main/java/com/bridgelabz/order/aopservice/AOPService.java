package com.bridgelabz.order.aopservice;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class AOPService {

    //logs method before hitting API
    @Before("execution(* com.bridgelabz.order.controller.OrderController.*(..))")
    public void logBeforeV1(JoinPoint joinPoint) {
        log.info("Initiating API : " + joinPoint.getSignature().getName() + " ");
    }

    //logs method After hitting API
    @After("execution(* com.bridgelabz.order.controller.OrderController.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("API successfully Executed : " + joinPoint.getSignature().getName() + " ");
    }

    //logs parameters After hitting API
    @After("execution(* com.bridgelabz.order.controller.OrderController.*(..))")
    public void logAfterAndSaveArgs(JoinPoint joinPoint) {
        log.info("After : " + Arrays.toString(joinPoint.getArgs()));
    }
}
