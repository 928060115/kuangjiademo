package com.example.kuangjiademo.config;

import com.example.kuangjiademo.interceptor.UserSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author liuyang
 * @since 2018/5/29 11:57
 */
@Configuration
public class WebMvcStaticConfig implements WebMvcConfigurer {

    @Autowired
    private UserSecurityInterceptor userSecurityInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("/page/login/login.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/login.js").addResourceLocations("classpath:/static/page/login/login.js");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userSecurityInterceptor).addPathPatterns("/");
    }
}
