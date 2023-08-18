package com.example.demo.filter;

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
    @Resource
    private RedisTemplate<String, String> session;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        String url = httpRequest.getRequestURL().toString();
        ValueOperations<String, String> operations = session.opsForValue();
        System.out.println("url: "+ url);
        if(url.contains("login")){
            System.out.println("登录页面，放行");
            String ID = httpRequest.getParameter("ID");
            operations.set("CURRENT_USER",ID,15, TimeUnit.MINUTES);
            System.out.println("ID: " + ID);
            chain.doFilter(request,response);
            return;
        }
        if(operations.get("CURRENT_USER") != null){ //如果在登录状态
            System.out.println("在登录状态");
            chain.doFilter(request,response);;
        }
        else{
            System.out.println("未登录！");
        }
    }

    @Override
    public void destroy(){
        ;
    }
}
