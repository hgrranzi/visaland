package com.hgrranzi.visaland.logging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class LogUserActionAspect {

    @Around("@annotation(LogUserAction)")
    public Object logUserAction(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String actionDescription = method.getAnnotation(LogUserAction.class).description();
        StringBuilder logMessage = new StringBuilder(actionDescription);

        Object result = joinPoint.proceed();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            logMessage.append(" {").append(authentication.getName()).append("}");
        }

        ServletRequestAttributes attributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            logMessage.append(" ").append(request.getMethod()).append(" ").append(request.getRequestURI());
            HttpServletResponse response = attributes.getResponse();
            if (response != null) {
                logMessage.append(" HTTP-response status code=").append(response.getStatus());
            }
        }
        log.info(logMessage.toString());
        return result;

    }

}