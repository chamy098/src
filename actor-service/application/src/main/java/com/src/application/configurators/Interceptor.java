package com.src.application.configurators;

import com.src.core.interfaces.IRequestCounterService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor
public class Interceptor {
    private final IRequestCounterService requestCounterService;


    @Pointcut("execution(* com.src.application.controllers.*.*(..)) && !within(com.src.application.controllers.RequestCounterController)")
    public void requestMapping() {
    }

    @Before("requestMapping()")
    public void countRequests() {
        System.out.println("Request intercepted.");
        requestCounterService.incrementRequestCount();
    }
}