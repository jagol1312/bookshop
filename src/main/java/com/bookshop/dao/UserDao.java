package com.bookshop.dao;

import com.bookshop.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDao {

    @Select("select * from user where userid=#{userid}")
    public User getUserInfoById(long userid);

    /*根据姓名查询用户*/
    @Select("select * from user where username = #{username} and roleid=#{roleid}")
    public User getUserInfoName(@Param("username")String username,@Param("roleid")int roleid);

    //注册/添加用户
    @Insert("insert into user(username,password,useraddress,email,age,sex,roleid) value(#{username},#{password},#{useraddress},#{email},#{age},#{sex},#{roleid})")
    public void adduser(User user);
    /*
    查询用户
     */
    @Select("select * from user where userid =#{userid}")
    public User getUserInfo(int userid);
    /*
    查询所有用户
     */
    @Select("select * from user ")
    public List<User> getUserInfoAll();
    /*
    修改用户
     */
    @Update("update user set username=#{username},password=#{password},useraddress=#{useraddress},email=#{email},age=#{age},sex=#{sex},roleid=#{roleid} where userid=#{userid}")
    public void updateuser(User user);
    /*
    删除用户
     */
    @Delete("delete from user where userid=#{userid}")
    public void deleteuser(long userid);
}
