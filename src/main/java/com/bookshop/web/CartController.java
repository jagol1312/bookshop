package com.bookshop.web;

import com.alibaba.fastjson.JSONObject;
import com.bookshop.BookshopApplication;
import com.bookshop.model.Book;
import com.bookshop.model.Cart;
import com.bookshop.model.User;
import com.bookshop.service.BookService;
import com.bookshop.service.CartService;
import com.bookshop.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private BookService bookService;

    /*
     *修改购物车中商品的数量
     *@param request cartid cartquantity
     *@return
     * */
    @RequestMapping("/editCart")
    public JSONObject editCart(HttpServletRequest request,long cartid,long cartquantity){
        JSONUtil jsonUtil = new JSONUtil();
        long userid = ((User) request.getSession().getAttribute("user")).getUserid();
      Cart cart=cartService.GetCartInfoByCartId(cartid);
      long bookid=cart.getBookid();
      Book book=bookService.GetBookInfoByBookId(bookid);
      double price=book.getPrice();//书本单价
     cart.getCartquantity();//购物车内的商品数量
      cart.getCarttotal();//购物车商品数量
      //long cartnumber=cart.getCartquantity()-cartquantity;
      double carttotal=cartquantity*price;
      cart.setCartquantity(cartquantity);
      cart.setCarttotal(carttotal);
      cartService.UpdateCartInfo(cart);
      return jsonUtil.success("修改成功");
    }
    /*
    *添加商品进购物车
    *@param bookid userid cartquantity;
    *@return Json
    * */
    @RequestMapping("/AddCart")
    public JSONObject AddCart(HttpServletRequest request,long bookid,long cartquantity){

        JSONUtil jsonUtil = new JSONUtil();
       // Cart cart=new Cart();
        long userid = ((User) request.getSession().getAttribute("user")).getUserid();//获得session用户id
        String id = request.getParameter("bookid");
        bookid = Long.parseLong(id);
        try {


            if (cartService.SelectCartByuserIdBookId(userid, bookid) == null) {
                Cart cart = new Cart();
                Book book = bookService.GetBookInfoByBookId(bookid);//获得书籍信息
                double price = (cartquantity * book.getPrice());//总价
                cart.setUserid(userid);
                cart.setBookid(bookid);
                cart.setCartquantity(cartquantity);
                cart.setCarttotal(price);
                cartService.AddCartInfo(cart);
                return jsonUtil.success("添加成功1");
            } else {

                Book book = bookService.GetBookInfoByBookId(bookid);//获得书籍信息
                Cart cart = cartService.SelectCartByuserIdBookId(userid, bookid);//获取购物车中书籍的信息
                double allprice = (cartquantity * book.getPrice());//计算金额
                double price = (cart.getCarttotal() + allprice);//购物车金额累加
                //double price = (cart.getCarttotal() + book.getPrice());
                long bnumber = cart.getCartquantity() + cartquantity;//数量累加
                cart.setCartquantity(bnumber);
                cart.setCarttotal(price);
                cartService.UpdateCartInfo(cart);
                return jsonUtil.success("添加成功2");
            }
        }catch (Exception ex){
            ex.printStackTrace();
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
    /**
     * 查询单个用户购物车内容
     * @param request userid
     * @return Json
     */
    @RequestMapping("/GetCartInfo")
    public List<Map> GetCartByUserid(HttpServletRequest request){
        long userid = ((User) request.getSession().getAttribute("user")).getUserid();
        System.out.println( cartService.GetCartInfos(userid));
        return cartService.GetCartInfos(userid);
    }


}
