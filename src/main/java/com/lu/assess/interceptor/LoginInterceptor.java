package com.lu.assess.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: helu
 * @date: 2022/7/27 22:23
 * @description:
 */


public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object obj = request.getSession().getAttribute("eid");
        if(obj==null){
            response.sendRedirect("http://139.196.213.114/");
            return false;
        }
        return true;
    }
}
