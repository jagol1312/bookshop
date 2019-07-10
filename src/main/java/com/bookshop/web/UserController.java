package com.bookshop.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bookshop.model.User;
import com.bookshop.service.UserService;
import com.bookshop.util.CodeUtil;
import com.bookshop.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //用户登录
    @RequestMapping(value = "/userlogin", method = RequestMethod.POST)
    protected JSONObject userlogin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.login(username, password, 2);
        JSONUtil jsonUtil = new JSONUtil();
        if (user == null) return jsonUtil.fail("用户名或者密码错误！");
//        else if (!CodeUtil.checkVerifyCode(request)) {
//            return jsonUtil.fail("验证码错误！");
//        }
        else {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return jsonUtil.success("登录成功！");
        }
    }

    //管理员登录
    @RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
    protected JSONObject adminlogin(HttpServletRequest request) {
        String adminname = request.getParameter("username");
        String password = request.getParameter("password");
        User adminInfo = userService.login(adminname, password, 3);
        JSONUtil jsonUtil = new JSONUtil();

        if (adminInfo == null) return jsonUtil.fail("管理员名或者密码错误！");
//        else if (!CodeUtil.checkVerifyCode(request)) {
//            return jsonUtil.fail("验证码错误！");
//        }
        else {
            request.getSession().setAttribute("admin", adminInfo);
            return jsonUtil.success("登录成功！");
        }
    }

}