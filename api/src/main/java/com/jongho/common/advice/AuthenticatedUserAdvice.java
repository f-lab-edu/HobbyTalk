package com.jongho.common.advice;

import com.jongho.common.annotaition.AuthenticatedUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
@Aspect
public class AuthenticatedUserAdvice {
    private final ThreadLocal<Long> userIdThreadLocal = new ThreadLocal<>();

    @Around("@within(com.jongho.common.annotaition.AuthenticatedUser)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < args.length; i++) {
            AuthenticatedUser authenticatedUserAnnotation = findAuthenticatedUserAnnotation(methodSignature, i);

            if (authenticatedUserAnnotation != null) {
                // Modify the argument value based on a condition (e.g., if it is a Long type)
                if (args[i] instanceof Long) {
                    // For example, modify Long type argument
                    args[i] = userIdThreadLocal.get();
                }
            }
        }
        return joinPoint.proceed();
    }

    private AuthenticatedUser findAuthenticatedUserAnnotation(MethodSignature methodSignature, int parameterIndex) {
        Annotation[] parameterAnnotations = methodSignature.getMethod().getParameterAnnotations()[parameterIndex];
        for (Annotation annotation : parameterAnnotations) {
            if (annotation.annotationType() == AuthenticatedUser.class) {
                return (AuthenticatedUser) annotation;
            }
        }
        return null;
    }
}
