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


}
