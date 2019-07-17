package com.bookshop.service;

import com.bookshop.dao.CartDao;
import com.bookshop.model.Book;
import com.bookshop.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
