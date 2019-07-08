package com.bookshop.service;

import com.bookshop.dao.AdminInfoDao;
import com.bookshop.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookshop.dao.UserDao;

import java.util.List;

@Service
public class UserService  {
    @Autowired
    private UserDao userdao;

    /*根据id查询*/
    public UserInfo getUserInfoById(long id){
        return this.userdao.getUserInfoById(id);
    }


/*显示用户表单信息*/
    public List<UserInfo> getUserInfoAll(){

         return userdao.getUserInfoAll();
    }

    //修改用户信息
    public void updateuser(UserInfo user){
        userdao.updateuser(user);
    }

    public void deleteuser(long id){
        userdao.deleteuser(id);
    }

/*注册*/
    public void adduserInfo(UserInfo user){
        this.userdao.adduser(user);
    }

/*登陆*/
    public UserInfo login(String username,String pwd){
        UserInfo user=this.userdao.getUserInfoName(username);
        if (user==null)
            return null;
        if (user.getPassword().equals(pwd))
            return user;
        return null;
    }


    //登陆验证
    public  String Selectusername(String name){
        String Name=userdao.selectUserName(name);
        return  Name;
    }

    public UserInfo getUserInfoName(String username)
    {
        return this.userdao.getUserInfoName(username);
    }


    //验证2
    public List<UserInfo> getUserInfoAllByname(String username){

      return userdao.getUserInfoAllByname(username);
    }

}
