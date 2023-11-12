package com.jongho.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class HttpRequestLoggingAdvice {
    @Before("@within(com.jongho.annotaition.HttpRequestLogging)")
    public void log(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        logger.info("Method: " + methodName + ", parameters: " + Arrays.toString(args));
    }


    private static final Logger logger = LoggerFactory.getLogger(HttpRequestLoggingAdvice.class);
}
