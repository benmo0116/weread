package com.wxy.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wxy
 * @create 2018-02-06 14:06
 * @desc ${DESCRIPTION}
 **/
@Aspect
@Component
public class LoginAspect {
    //在每个页面加载前执行 加载登录用户信息
    @Pointcut("execution(public * com.wxy.controller.LoginController.*(..))")
    public void getUser(){}

    @Before("getUser()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
    }

    @AfterReturning(returning = "ret", pointcut = "getUser()")
    public void doAfterReturning(Object ret) throws Throwable {

    }

}
