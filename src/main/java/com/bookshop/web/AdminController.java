package com.bookshop.web;

import com.alibaba.fastjson.JSONObject;
import com.bookshop.model.AdminInfo;
import com.bookshop.service.AdminService;
import com.bookshop.service.UserService;
import com.bookshop.util.CodeUtil;
import com.bookshop.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    //管理员登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    protected JSONObject login(HttpServletRequest request) {
        String adminname = request.getParameter("username");
        String password = request.getParameter("password");
        AdminInfo adminInfo = adminService.login(adminname, password);
        JSONUtil jsonUtil = new JSONUtil();

        if(adminInfo == null) return jsonUtil.fail("管理员名或者密码错误！");
        else if(!CodeUtil.checkVerifyCode(request)){
            return jsonUtil.fail("验证码错误！");
        }
        else {
            request.getSession().setAttribute("admin", adminInfo.getName());
            return jsonUtil.success("登录成功！");
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    protected JSONObject loginout(HttpServletRequest request,HttpSession session) {
        JSONUtil jsonUtil = new JSONUtil();
        //if(adminService.get("username")==null)
        //{
        //    return jsonUtil.success("请先登录！");
        //}
        //adminService.delete("adminname");
        request.getSession().removeAttribute("admin");
        //session.invalidate();
        return jsonUtil.success("退出成功！");
    }

    /*
     *
     *
     *
     *
     * */
}