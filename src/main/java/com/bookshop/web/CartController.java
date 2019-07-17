package com.bookshop.web;

import com.alibaba.fastjson.JSONObject;
import com.bookshop.model.Cart;
import com.bookshop.model.User;
import com.bookshop.service.CartService;
import com.bookshop.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/Cart")
public class CartController {
    @Autowired
    private CartService cartService;

    /*
    *添加商品进购物车
    *@param bookid userid cartquantity carttotal;
    *@return Json
    * */
    @PostMapping("/AddCart")
    public JSONObject AddCart(HttpServletRequest request, Cart cart) {

        JSONUtil jsonUtil = new JSONUtil();
        String id = request.getParameter("bookid");
        long userid = ((User) request.getSession().getAttribute("user")).getUserid();
        long cartquantity = Long.parseLong(request.getParameter("cartquantity"));
        long carttotal = Long.parseLong(request.getParameter("carttotal"));
        long bookid = Long.parseLong(id);

        try {
            cart.setUserid(userid);
            cart.setBookid(bookid);
            cart.setCartquantity(cartquantity);
            cart.setCarttotal(carttotal);
            cartService.AddCartInfo(cart);

            return jsonUtil.success("添加成功");
        } catch (Exception ex) {
            return jsonUtil.fail("添加失败");
        }
    }
    /**
     * 清空用户购物车
     * @param userid
     * @return json
     */
    @RequestMapping("/DeleteAllCart")
    public JSONObject DeleteAllCart(HttpServletRequest request,long userid){
        JSONUtil jsonUtil = new JSONUtil();
        userid = ((User) request.getSession().getAttribute("user")).getUserid();
        if(!cartService.SelectCartByuserid(userid).isEmpty()){
            cartService.DeleteAllCartByUserId(userid);
            return jsonUtil.success("删除成功");

        }
        else{
            return jsonUtil.fail("删除失败");
        }
    }
    /**
     * 删除单个商品
     * @param cartid
     * @return Json
     */
    @RequestMapping("/DeleteCart")
    public JSONObject DeleteCart(long cartid){
        JSONUtil jsonUtil = new JSONUtil();

        if (cartService.SelectCartByCartId(cartid)==null) {
            return jsonUtil.fail("删除失败");
        }
        else {
            cartService.DeleteAllCartByCartId(cartid);
            return jsonUtil.success("删除成功");
        }

    }


}
