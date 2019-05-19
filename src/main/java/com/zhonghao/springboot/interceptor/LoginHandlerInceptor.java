package com.zhonghao.springboot.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定义登录拦截器，需要添加到容器
 */
public class LoginHandlerInceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser != null) {
//            放行
            return true;
        }
        request.setAttribute("msg","没有权限，请先登录！");
        request.getRequestDispatcher("/index.html").forward(request,response);
        return false;
    }
}
