package com.example.oldcar.aspect;

import com.example.oldcar.domain.AccessLog;
import com.example.oldcar.domain.User;
import com.example.oldcar.service.AccessLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
public class HttpAspect {
    @Autowired
    private AccessLogService accessLogService;

    AccessLog accessLog = new AccessLog();

    /**
     * 获取日志对象
     */
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    /**
     * 拦截
     */
    @Pointcut("execution(public * com.example.oldcar.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        logger.info("\r\n\r\n================log start====================");


        // url
        logger.info("url = {}", request.getRequestURL());

        // method
        logger.info("method = {}", request.getMethod());

        // ip
        logger.info("ip = {}", request.getRemoteAddr());

        // 类方法
        logger.info("class_method = {}",
                joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        // 参数
        logger.info("args = {}", joinPoint.getArgs());

        logger.info("\r\n================log end====================\r\n\r\n");

        //读取session中的用户
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        accessLog.setUser(user);

        //访问url
        accessLog.setUrl(request.getRequestURL().toString());
        //请求方式
        accessLog.setMethod(request.getMethod());
        //用户ip
        accessLog.setIp(request.getRemoteAddr());
        //访问类方法
        accessLog.setClassMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //请求参数
        accessLog.setArgs(Arrays.toString(joinPoint.getArgs()));
        //访问时间
        accessLog.setDate(new Date());
        accessLogService.add(accessLog);
    }

    @After("log()")
    public void doAfter() {

    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", object.toString());
    }
}
