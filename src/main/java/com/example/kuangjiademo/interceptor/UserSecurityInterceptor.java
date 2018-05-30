package com.example.kuangjiademo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * session 拦截器
 * @author liuyang
 * @since 2018/5/30 15:39
 */
@Component
public class UserSecurityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object Authorization = request.getSession().getAttribute("Authorization");
        if (Authorization == null || !(Authorization instanceof Object)){
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }
}
