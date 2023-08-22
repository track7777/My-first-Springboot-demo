package com.example.demo.filter;

import com.example.demo.controller.UserController;
import com.example.demo.entity.User;
import com.example.demo.service.impl.UserServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


@WebFilter("/*") //对所有请求进行拦截
public class MyFilter implements Filter{

    @Autowired
    private UserServiceImpl userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        String url = httpRequest.getRequestURL().toString();
        if(url.contains("login") || url.contains("logout")){
            chain.doFilter(request, response);
            System.out.println("登录或登出页面，直接放行");
        }
        else{
            String session = ((HttpServletRequest) request).getHeader("session");
            System.out.println("请求中的session is " + session);
            if(session != null && userService.getRedisTemplate().hasKey(session)){
                ValueOperations<String, Integer> operations = userService.getRedisTemplate().opsForValue();
                Integer ID = operations.get(session);
                System.out.println("已登录 ID：" + ID);
                chain.doFilter(request,response);
            }
            else{
                System.out.println("未登录！");
            }
        }
    }

    @Override
    public void destroy(){
        ;
    }
}
