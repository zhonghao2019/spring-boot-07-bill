package com.zhonghao.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    /**
     * 登录
     * @param session
     * @param username
     * @param password
     * @param map
     * @return
     */
    @PostMapping("/login")
    public String login(HttpSession session, String username, String password, Map<String,Object> map) {
        if(!StringUtils.isEmpty(username) && "123".equals(password)) {
            session.setAttribute("loginUser",username);
//            重定向，去视图控制器
            return "redirect:/main.html";
        }
        map.put("msg","用户名或密码错误");
        return "main/login";
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        session.invalidate();
        return "redirect:/index.html";
    }
}
