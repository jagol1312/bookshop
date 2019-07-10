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
    /*
    注册
     */
    @PostMapping("/register")
    public JSONObject add(HttpServletRequest request, User user){
        String username = request.getParameter("username");
        JSONUtil jsonUtil = new JSONUtil();
        if (!userService.Selectusername(username)){
            userService.adduserInfo(user);
            return jsonUtil.success("注册成功！");
        }
//        else if(!CodeUtil.checkVerifyCode(request)){
//            return jsonUtil.fail("验证码错误！");
//        }
        else {

            return jsonUtil.fail("注册失败，用户已存在！");
        }
    }

    /*
    用户退出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    protected JSONObject logout(HttpServletRequest request) {
        JSONUtil jsonUtil = new JSONUtil();
        request.getSession().removeAttribute("user");
        return jsonUtil.success("退出成功！");
    }
    /*
    修改用户
     */
    @PostMapping("/edit")
    public JSONObject edit(HttpServletRequest request, User user){
        JSONUtil jsonUtil = new JSONUtil();
            this.userService.updateuser(user);
            return jsonUtil.success("修改成功！");
    }
    /*
    用户列表
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request){
        return JSON.toJSONString(userService.getUserInfoAll());
    }

}