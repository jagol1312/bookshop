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

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

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

    /*注册 or 添加*/
    public void adduserInfo(User user){
        this.userdao.adduser(user);
    }
    /*
    检测是否重名
     */
    public  boolean Selectusername(String name){
        List<User> user=userdao.getUserInfoAll();
        return user.contains(name);
    }
    /*
    修改用户
     */
    public void updateuser(User user){
        userdao.updateuser(user);
    }
    /*
    查询用户信息
     */
    public User getUserInfo(long userid){
        return userdao.getUserInfo(userid);
    }
    /*
        查询所有用户
         */
    public List<User> getUserInfoAll(){
        return userdao.getUserInfoAll();
    }
    /*
    删除用户
     */
    public int deleteuser(long id){
        cartService.DeleteAllCartByUserId(id);
        orderService.deleteorderbyuserid(id);
        return userdao.deleteuser(id);
    }
}
