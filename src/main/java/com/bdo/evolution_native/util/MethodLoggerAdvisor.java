package com.bdo.evolution_native.util;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

/**
 * This is for Method Logger Advisor
 */
@Aspect
@Component
@Slf4j
public class MethodLoggerAdvisor {

    @SneakyThrows
    @Around("@annotation(MethodLogger) && execution(* * (..))")
    public Object methodDetails(final ProceedingJoinPoint joinPoint) {
        final Object result;
        final String message = getMessage(joinPoint);
        log.debug(EvolutionConstantUtils.METHOD_START + message);
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        result = joinPoint.proceed();
        stopWatch.stop();
        log.debug(EvolutionConstantUtils.METHOD_END + message);
        log.debug(EvolutionConstantUtils.METHOD_EXECUTION_TIME + stopWatch.getTotalTimeMillis());
        return result;
    }

    private String getMessage(final JoinPoint joinPoint) {
        final StringBuilder sb = new StringBuilder();
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final Method method = methodSignature.getMethod();
        sb.append(method.getDeclaringClass()).append(".").append(method.getName()).append(" ");
        return sb.toString();
    }

}
