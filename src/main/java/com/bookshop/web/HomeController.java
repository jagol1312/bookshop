package com.bookshop.web;
import com.bookshop.model.BookInfo;
import com.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = {"/getpopularbooks"},method = RequestMethod.GET)
    public List<BookInfo> getpopularbooks() {
        return bookService.GetFrontOfBookInfo(4);
    }

    @RequestMapping(value = {"/getnewbooks"},method = RequestMethod.GET)
    public List<BookInfo> getnewbooks() {
        return bookService.GetFrontOfBookInfo(8);
    }

    /*
    *
    *
    *
    *
    * */

    @RequestMapping("book/product-categonries")
    public String product_categonries(){
        return "/book/product-categonries";
    }

    @RequestMapping("book/list")
    public String list(){
        return "/book/list";
    }
    /*
    *
    *Contact*/
    @RequestMapping("/Contact")
    public String Contact(){
        return "/Contact/Contact";
    }

    @RequestMapping("/Contact/Contactus")
    public String ContactUs(){
        return "redirect:/";
    }
    /**
     * MyAccount
     */
    @RequestMapping("/MyAccount/address")
    public String address(){
        return "MyAccount/address";
    }

    @RequestMapping("/MyAccount/edit_account")
    public String edit_account(){
        return "/MyAccount/edit-account";
    }

    @RequestMapping("/MyAccount/edit_address")
    public String edit_address(){
        return "/MyAccount/edit-address";
    }

    @RequestMapping("/MyAccount/login")
    public String login(){
        return "/MyAccount/login";
    }

    @RequestMapping("/MyAccount/MyAccount")
    public String myAccount(){
        return "/MyAccount/MyAccount";
    }

    @RequestMapping("/MyAccount/order_received")
    public String order_received(){
        return "/MyAccount/order-received";
    }

    @RequestMapping("/MyAccount/orders")
    public String orders(){
        return "/MyAccount/orders";
    }

    @RequestMapping("/MyAccount/cart")
    public String view_cart(){
        return "/MyAccount/booklist";
    }

    @RequestMapping("/MyAccount/view_order")
    public String view_order(){
        return "/MyAccount/view-order";
    }
    /**
     * user
     */
    @RequestMapping("/user/checkout")
    public String checkout(){
        return "/checkout";
    }

    @RequestMapping("/user/adduser")
    public String checkout1(){
        return "user/adduser";
    }
}
