package com.nanrui.userscore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ClassName loginController
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/15 11:42
 * @Version 1.0
 **/
@Controller
public class LoginController {

    @PostMapping("/user/login")
    public String loginPast(@RequestParam("userName")String userName,
                            @RequestParam("passWord") String passWord,
                            Map<String,Object> map, HttpSession session){
        if (!StringUtils.isEmpty(userName) && "123".equals(passWord)){
            //登陆成功，防止表单重复提交，可以重定向到主页
            session.setAttribute("loginUser",userName);
            System.out.println(userName);
            return "redirect:/main.html";
        } else {
            //登陆失败
            map.put("msg","用户名密码错误");
            return "login";
        }

    }

}
