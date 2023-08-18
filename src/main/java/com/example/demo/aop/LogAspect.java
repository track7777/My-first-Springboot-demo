package com.example.demo.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class LogAspect {

    @Pointcut("execution (* com.example.demo.controller.*.*(..))")
    //                 返回值类型                         类 方法 参数
    public void cut(){
    }

    @Before("cut()")
    public void beforeRun(){
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String dateStr = sdf.format(date);
        System.out.println("——————————————request time： "+ dateStr + "——————————————");
    }

    @After("cut()")
    public void afterRun(){
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String dateStr = sdf.format(date);
        System.out.println("——————————————complete time： " + dateStr + "——————————————");
    }
}
