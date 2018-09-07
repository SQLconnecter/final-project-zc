package com.baizhi.aspect;

import com.baizhi.entity.Log;
import com.baizhi.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;


@Configuration
@Aspect
public class LoggerAspect {
    @Autowired
    private LogService logService;
    @Pointcut(value = "@annotation(com.baizhi.aspect.LogAnnotation)")
    public void pointcut(){

    }
    @Around("pointcut()")
    public Object log(ProceedingJoinPoint proceedingJoinPoint){
        ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        /*String user =(String) session.getAttribute("user");*/
        String uid="1";
        Date date = new Date();

        MethodSignature signature = (MethodSignature)proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
        String name = annotation.name();
        Object proceed =null;
        Boolean flag=false;
        try {
            proceed = proceedingJoinPoint.proceed();
            flag=true;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //对日志的入库操作
        String uuid = UUID.randomUUID().toString();
        Log log = new Log();
        log.setId(uuid);
        log.setDate(date);
        log.setUid(uid);
        log.setOperate(name);
        log.setResult(flag);
        logService.put(log);
        return proceed;
    }
}
