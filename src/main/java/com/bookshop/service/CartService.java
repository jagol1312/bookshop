package com.bookshop.service;

import com.bookshop.dao.CartDao;
import com.bookshop.model.Book;
import com.bookshop.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartService {
    @Autowired
    private CartDao cartDao;

   /*添加购物车*/
    public void AddCartInfo(Cart cart){
        cartDao.AddCart(cart);
    }
    /*删除全部购物车*/
    public void  DeleteAllCartByUserId(long userid){
        cartDao.DeleteAllCartByUserId(userid);
    }
    /*删除购物车单项*/
    public void  DeleteAllCartByCartId(long cartid){
        cartDao.DeleteCartByCartId(cartid);
    }
    /*查询所有订单项*/
    public List<Cart> SelectAllCart(){
        return cartDao.SelectAllCart();
    }
    /*根据用户id查询订单项*/
    public List<Cart> SelectCartByuserid(long userid){
        return cartDao.SelectCartByUserid(userid);
    }
    /*查询cartid*/
    public String SelectCartByCartId(long cartid){
        return cartDao.SelectCartIdByCartId(cartid);
    }
    /*查询商品数量*/
    public Cart SelectCartByuserIdBookId(long userid,long bookid){
        return cartDao.SelectCartByUserIdBookId(userid,bookid);
    }
    /*修改商品数量金额*/
    public void UpdateCartInfo(Cart cart){
        cartDao.UpdateCartInfo(cart);
    }
    /*查询单个用户购物车内容*/
    public List<Map> GetCartInfos(long userid){
       return cartDao.SelectCartInfoByUserId(userid);
    }
    /*根据cartid查询购物车内容*/
    public Cart GetCartInfoByCartId(long cartid){
        return cartDao.SelectCartInfoByCartId(cartid);
    }
    /*user购物车总价*/
    public double SelectCartAmount(long userid){
        return cartDao.SelectCartAmount(userid);
    }
}
