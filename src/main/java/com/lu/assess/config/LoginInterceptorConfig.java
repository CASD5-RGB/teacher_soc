package com.lu.assess.config;

import com.lu.assess.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/27 22:30
 * @description:
 */
//@Configuration
//public class LoginInterceptorConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        HandlerInterceptor loginInterceptor = new LoginInterceptor();
//        List<String> patterns = new ArrayList<String>();
//
//        //patterns.add("139.196.213.114:8081/employee/login");
//        patterns.add("/employee/login");
//        patterns.add("http://139.196.213.114/");
//
//        //通过注册工具添加拦截器
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns(patterns);
//    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//
//        registry.addMapping("/**")
//                //是否发送Cookie
//                .allowCredentials(true)
//                //放行哪些原始域
//                .allowedOriginPatterns("*")
//                //请求方法
//                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
//                .allowedHeaders("*")
//                .exposedHeaders("*");
//    }
//}
