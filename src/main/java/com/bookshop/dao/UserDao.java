package com.bookshop.dao;

import com.bookshop.model.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDao {

    @Select("select * from userinfo where id=#{id}")
    public UserInfo getUserInfoById(long id);

    /*
    * 修改*/
    @Update("update userinfo set username=#{username},password=#{password},phone=#{phone},email=#{email},address=#{address},postcode=#{postcode} where id=#{id}")
    public void updateuser(UserInfo user);

    @Delete("delete from userinfo where id=#{id}")
    public void deleteuser(long id);

    @Select("select * from userinfo ")
    public List<UserInfo> getUserInfoAll();

    /*根据姓名查询*/
    @Select("select * from userinfo where username = #{username}")
    public UserInfo getUserInfoName(String username);

    /*用户修改、添加验证*/
    @Select("select username from userinfo where username=#{'username'}" )
    public String selectUserName(String name);

    /*用户修改个人信息表*/
    @Select("select * from userinfo where username=#{username}")
    public List<UserInfo> getUserInfoAllByname(String username);

//注册/添加用户
    @Insert("insert into userinfo(username,password,phone,email,address,postcode) value(#{username},#{password},#{phone},#{email},#{address},#{postcode})")
    public void adduser(UserInfo user);

    @Select("select * from admininfo where username = #{username}")
    public UserInfo adminLogin();
}
