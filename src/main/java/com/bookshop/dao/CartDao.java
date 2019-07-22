package com.bookshop.dao;

import com.bookshop.model.Cart;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface CartDao {
    /*添加购物车*/
    @Insert("insert into cart( userid,bookid,cartquantity,carttotal) value(#{userid},#{bookid},#{cartquantity},#{carttotal})")
    public void AddCart( Cart cart);
    /*删除购物车*/
    @Delete("delete from cart where userid=#{userid}")
    public void DeleteAllCartByUserId(Long userid);
    /*删除购物车内单项内容*/
    @Delete("delete from cart where cartid=#{cartid}")
   public void DeleteCartByCartId(Long userid);
    /*所有用户购物车项*/
    @Select("select * from cart")
    public List<Cart> SelectAllCart();
    /*根据用户id查询用户订单项*/
    @Select("select * from cart where userid=#{userid}")
    public List<Cart> SelectCartByUserid(long userid);
    /*查询是否存在*/
    @Select("select cartid from cart where cartid=#{cartid}")
    public String SelectCartIdByCartId(long cartid);
    /*双条件查询单个商品*/
    @Select("select * from cart where userid=#{userid} and bookid=#{bookid}")
    public Cart SelectCartByUserIdBookId(@Param("userid") long userid,@Param("bookid") long bookid);
    /*修改商品数量 金额*/
    @Update("update cart set cartquantity=#{cartquantity},carttotal=#{carttotal} where bookid=#{bookid} ")
    public void UpdateCartInfo(Cart cart);
    /*显示购物车*/
    @Select("SELECT cart.cartid,book.bookid,book.picname,cart.cartquantity,book.bookname,book.price FROM book right JOIN cart ON book.bookid=cart.bookid and cart.userid=#{userid}")
    public List<Map> SelectCartInfoByUserId(long userid);
    /*根据cartid查询购物车内容*/
    @Select("Select * from cart where cartid=#{cartid}")
    public Cart SelectCartInfoByCartId(long cartid);
    /*user购物车总价*/
    @Select("SELECT SUM(carttotal) from cart WHERE userid=#{userid}")
    public double SelectCartAmount(long userid);
    @Select("selete book.bookname FROM book JOIN cart on cart.bookid = book.bookid and cart.userid = #{userid}")
    public String getcartbooksname(long userid);
}
