package com.bookshop.service;

import com.bookshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookshop.dao.UserDao;

import java.util.List;

@Service
public class UserService  {
    @Autowired
    private UserDao userdao;

    /*根据id查询*/
    public User getUserInfoById(long id){
        return this.userdao.getUserInfoById(id);
    }



/*登陆*/
    public User login(String username,String pwd,int rid){

        User user=this.userdao.getUserInfoName(username,rid);
        System.out.println(user);

        if (user==null)
            return null;
        if (user.getPassword().equals(pwd))
            return user;
        return null;
    }





}
