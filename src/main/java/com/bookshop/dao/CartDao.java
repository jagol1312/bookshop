package com.bookshop.dao;

import com.bookshop.model.Cart;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper
@Component
public interface CartDao {
    /*添加购物车*/
    @Insert("insert into cart( userid,bookid,cartquantity,carttotal) value(#{userid},#{booid},#{cartquantity},#{carttotal})")
    public void AddCart( Cart cart);
    /*删除购物车*/
    @Delete("delete from cart where userid=#{userid}")
    public void DeleteAllCartByUserId(Long userid);
    /*删除购物车内单项内容*/
    @Delete("delete from cart where cartid=#{cartid}")
   public void DeleteCartByCartId(Long userid);
    /*查询所有购物车项*/
    @Select("select * from cart")
    public List<Cart> SelectAllCart();
    /*根据用户id查询用户订单项*/
    @Select("select * from cart where userid=#{userid}")
    public List<Cart> SelectCartByUserid(long userid);
    /*查询是否存在*/
    @Select("select cartid from cart where cartid=#{cartid}")
    public String SelectCartIdByCartId(long cartid);


}
