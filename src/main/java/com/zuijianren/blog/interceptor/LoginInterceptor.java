package com.zuijianren.blog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("loginInterceptor");
        Object user = request.getSession().getAttribute("user");
        if(user == null){
            response.sendRedirect(request.getContextPath()+"/admin");
            return false;
        }else{
            return true;
        }
    }
}
