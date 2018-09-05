package com.baizhi.aspect;

import com.baizhi.annotation.LogAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

@Configuration
@Aspect
public class LoggerAspect {
    private Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
    @Pointcut(value="@annotation(LogAnnotation)")
    public void pointcut(){

    }
    @Around("pointcut()")
    public Object log(ProceedingJoinPoint proceedingJoinPoint){
        ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        String user =(String) session.getAttribute("user");

        Date date = new Date();

        MethodSignature signature = (MethodSignature)proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
        String name = annotation.value();
        Object proceed =null;
        Boolean flag=false;
        try {
            proceed = proceedingJoinPoint.proceed();
            flag=true;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println(flag);
        return proceed;
    }
}
