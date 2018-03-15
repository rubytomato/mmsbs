package com.example.domain.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Aspect
@Component
public class DataSourceAop {

    @Around("target(datasource)")
    public Object pointcut(ProceedingJoinPoint pjp, DataSource datasource) throws Throwable {
        System.out.println("[" + pjp.getSignature() + "] datasource : " + datasource.toString());
        return pjp.proceed();
    }

}
