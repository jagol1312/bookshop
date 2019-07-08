package com.bookshop.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bookshop.model.AdminInfo;
import com.bookshop.model.UserInfo;
import com.bookshop.service.UserService;
import com.bookshop.util.CodeUtil;
import com.bookshop.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
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

    //登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    protected JSONObject login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserInfo userInfo = userService.login(username, password);
        JSONUtil jsonUtil = new JSONUtil();
        if (userInfo == null) return jsonUtil.fail("用户名或者密码错误！");
        else if(!CodeUtil.checkVerifyCode(request)){
            return jsonUtil.fail("验证码错误！");
        }
        else {
            HttpSession session =  request.getSession();
            session.setAttribute("user", userInfo.getUsername());
            return jsonUtil.success("登录成功！");
        }
    }

    //显示用户列表
    @RequestMapping("/list")
    public String list(HttpServletRequest request){
        String adminName =  request.getSession().getAttribute("admin").toString();
        if(adminName == null)
        {
            return JSON.toJSONString("管理员未登录，或者没有权限！");
        }
        return JSON.toJSONString(userService.getUserInfoAll());
    }


    @PostMapping("/register")
    public JSONObject add(HttpServletRequest request, UserInfo user){
        String username = request.getParameter("username");
        String userIsExisted = userService.Selectusername(username);
        JSONUtil jsonUtil = new JSONUtil();
        if (userIsExisted == null){
            userService.adduserInfo(user);
            return jsonUtil.success("注册成功！");
        }
        else if(!CodeUtil.checkVerifyCode(request)){
            return jsonUtil.fail("验证码错误！");
        }
        else {

            return jsonUtil.fail("注册失败，用户已存在！");
        }
    }

    @PostMapping("/editmyinfo")
    public JSONObject editmyinfo(HttpServletRequest request, UserInfo userInfo){
        JSONUtil jsonUtil = new JSONUtil();
        userInfo.setId(0);
        String username =request.getSession().getAttribute("user").toString();
        if(username == null)
        {
            return jsonUtil.fail("用户未登录！");
        }
        this.userService.updateuser(userInfo);
        return jsonUtil.success("修改成功！");
    }

    @RequestMapping(value="/edit",method = RequestMethod.POST)
    public JSONObject edit(HttpServletRequest request, UserInfo userInfo){
        JSONUtil jsonUtil = new JSONUtil();
        String adminName =  request.getSession().getAttribute("admin").toString();
        if(adminName==null)
        {
            return jsonUtil.success("管理员未登录，或者没有权限！");
        }

        this.userService.updateuser(userInfo);
        return jsonUtil.success("修改成功！");
    }

    @RequestMapping("/delete")
    public JSONObject delete(HttpServletRequest request, long id){
        JSONUtil jsonUtil = new JSONUtil();
        String adminName =  request.getSession().getAttribute("admin").toString();
        if(adminName == null)
        {
            return  jsonUtil.fail("删除失败，管理员未登录！");
        }
        this.userService.deleteuser(id);
        return jsonUtil.success("删除成功！");
    }

    @GetMapping("/SelectByUserId")
    public String GetUserById(long userid,HttpServletRequest request)
    {
        String adminName =  request.getSession().getAttribute("admin").toString();
        if(adminName==null)
        {
            return JSON.toJSONString("管理员未登录，或者没有权限！");
        }
        return JSON.toJSONString(userService.getUserInfoById(userid));
    }

    @GetMapping("/SelectByUserSession")
    public String GetUserById(HttpServletRequest request)
    {
        String username =request.getSession().getAttribute("user").toString();
        if(username == null)
        {
            return JSON.toJSONString("用户未登录！");
        }
        return JSON.toJSONString(userService.getUserInfoName(username));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    protected JSONObject logout(HttpServletRequest request) {
        JSONUtil jsonUtil = new JSONUtil();
        /*if(userService.get("username")==null)
        {
            return jsonUtil.success("请先登录！");
        }
        userService.delete("username");*/
        request.getSession().removeAttribute("user");
        return jsonUtil.success("退出成功！");
    }

    ////////////////////////////////////////////////////////////////

    //Admin修改用户
    @RequestMapping(value="/admin/useredit",method = RequestMethod.GET)
    public String edit(long id,Model model){
        //this.id=id;
        UserInfo userInfo = this.userService.getUserInfoById(id);
        model.addAttribute("users",userInfo);
        return "admin/useredit";

    }

    @RequestMapping(value="/edit",method = RequestMethod.GET)
    public String edita(long id,Model model){
        UserInfo userInfo = this.userService.getUserInfoById(id);
        model.addAttribute("users",userInfo);
        return "";
    }







    /*用户注册跳转到注册页面*/
    @RequestMapping(value = "/MyAccount/register",method = RequestMethod.GET)
    public String add() { return "/MyAccount/register"; }
    /*注册完成后返回登陆界面*/


/*用户个人信息表*/
    @RequestMapping("/MyAccount/usermodifylist")
    public String onlyuserlist(Model model,HttpServletRequest request){
        UserInfo userInfo =(UserInfo) request.getSession().getAttribute("user");

        List<UserInfo> UserInfos = this.userService.getUserInfoAllByname(userInfo.getUsername());

        model.addAttribute("usersmodify",UserInfos);

        return "MyAccount/usermodifylist";
    }
    /*用户个人修改*/

    @RequestMapping(value="/user/edituser",method = RequestMethod.GET)
    public String useredit(long id,Model model){
        UserInfo userInfo = this.userService.getUserInfoById(id);
        model.addAttribute("users",userInfo);
        return "user/edituser";

    }
    @RequestMapping(value="/user/edituser",method = RequestMethod.POST)
    public String useredit(UserInfo userInfo){
        this.userService.updateuser(userInfo);

        return "redirect:/MyAccount/usermodifylist";

    }


}
