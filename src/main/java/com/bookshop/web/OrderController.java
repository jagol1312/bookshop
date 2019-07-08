package com.bookshop.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bookshop.model.BookInfo;
import com.bookshop.model.OrderInfo;
import com.bookshop.model.UserInfo;
import com.bookshop.service.BookService;
import com.bookshop.service.OrderService;
import com.bookshop.service.UserService;
import com.bookshop.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @GetMapping("/AddToOrder")
    public JSONObject AddToOrder(HttpServletRequest request)
    {
        JSONUtil jsonUtil = new JSONUtil();
        String username =request.getSession().getAttribute("user").toString();
        if(username == null)
        {
            return jsonUtil.fail("用户未登录！");
        }
        long bookid = Long.parseLong(request.getParameter("bookid"));
        int quantity =Integer.parseInt(request.getParameter("num"));
        OrderInfo orderInfo = new OrderInfo();

        orderInfo.setUsername(username);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        orderInfo.setOrder_date(df.format(new Date()).toString());
        orderInfo.setBook_id(bookid);
        BookInfo book=bookService.GetBookInfoByBookId(bookid);
        orderInfo.setBook_title(book.getTitle());
        orderInfo.setQuantity(quantity);
        orderInfo.setAddress(userService.getUserInfoName(username).getAddress());
        orderInfo.setStatus(0);
        orderInfo.setPrice(bookService.GetBookInfoByBookId(bookid).getPrice() * quantity);
        orderService.AddToOrder(orderInfo);
        return jsonUtil.success("添加成功！");
    }

    @GetMapping("/SelectAll")
    public String GetAllOrderInfo(HttpServletRequest request)
    {
        System.out.println(request.getSession().getAttribute("user"));
        String username =request.getSession().getAttribute("user").toString();
        if(username == null)
        {
            return JSON.toJSONString("用户未登录！");
        }
        List<OrderInfo> orderInfos=orderService.GetAllOrderInfo(username);
        List<BookInfo> bookInfos=orderService.GetAllBookInfoInOrder(username);
        ArrayList list = new ArrayList();
        for (int i=0;i<bookInfos.size();i++)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orderid",orderInfos.get(i).getOrder_id());
            map.put("username",orderInfos.get(i).getUsername());
            map.put("totalprice",orderInfos.get(i).getPrice());
            map.put("quantity",orderInfos.get(i).getQuantity());
            map.put("bookId",bookInfos.get(i).getBookId());
            map.put("title",bookInfos.get(i).getTitle());
            map.put("price",bookInfos.get(i).getPrice());
            map.put("picname",bookInfos.get(i).getPicname());
            list.add(map);
        }
        JSON.toJSONString(list);
        return JSON.toJSONString(list);
    }

    @GetMapping("/totalprice")
    public double GetTotalPrice(HttpServletRequest request)
    {
        String username =request.getSession().getAttribute("user").toString();
        if(username == null)
        {
            return -1;
        }
        List<OrderInfo> orderInfos=orderService.GetAllOrderInfo(username);
        List<BookInfo> bookInfos=orderService.GetAllBookInfoInOrder(username);
        ArrayList list = new ArrayList();
        float totalprice=0;
        for (int i=0;i<bookInfos.size();i++) {
            totalprice+=orderInfos.get(i).getPrice();
        }
        BigDecimal bigDecimal = new BigDecimal(totalprice);
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @GetMapping(value = "/AddToOrderbyOne")
    public JSONObject AddToOrderbyOne(long bookid, HttpServletRequest request)
    {
        OrderInfo orderInfo = new OrderInfo();
        JSONUtil jsonUtil = new JSONUtil();
        String username =request.getSession().getAttribute("user").toString();
        if(username == null)
        {
            return jsonUtil.fail("用户未登录！");
        }
        orderInfo.setUsername(username);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        orderInfo.setOrder_date(df.format(new Date()).toString());
        orderInfo.setBook_id(bookid);
        BookInfo book = bookService.GetBookInfoByBookId(bookid);
        orderInfo.setBook_title(book.getTitle());
        orderInfo.setQuantity(1);
        orderInfo.setAddress(userService.getUserInfoName(username).getAddress());
        orderInfo.setStatus(0);
        orderInfo.setPrice(bookService.GetBookInfoByBookId(bookid).getPrice());
        orderService.AddToOrder(orderInfo);
        return jsonUtil.success("添加成功！");
    }

    @GetMapping("/Delete")
    public JSONObject DeleteCartInfo(long orderid, HttpServletRequest request)
    {
        JSONUtil jsonUtil = new JSONUtil();
        String username =request.getSession().getAttribute("user").toString();
        if(username == null)
        {
            return jsonUtil.fail("用户未登录！");
        }
        String result = orderService.DeleteOrderInfo(orderid);
        if(result.equals("删除成功！"))
            return jsonUtil.success(result);
        else return jsonUtil.fail(result);
    }

    @GetMapping("/Buy")
    public JSONObject Buy(HttpServletRequest request)
    {

        JSONUtil jsonUtil = new JSONUtil();

        String username =request.getSession().getAttribute("user").toString();
        if(username == null)
        {
            return jsonUtil.fail("用户未登录！");
        }
        List<OrderInfo> orderInfos=orderService.GetAllOrderInfo(username);
        for(int i=0;i<orderInfos.size();i++)
        {
            orderService.Buy(orderInfos.get(i).getOrder_id());
        }
        return jsonUtil.success("购买成功！");
    }

    /*
    *
    *
    *
    *
    * */

    @GetMapping("/SelecetAllStatus1")
    public String GetAllOrderInfoStatus_1(HttpServletRequest request, Model model)
    {
        UserInfo userInfo =(UserInfo) request.getSession().getAttribute("user");
        model.addAttribute("cart", orderService.GetAllOrderInfoStatus_1("user"));
        model.addAttribute("cartbook", orderService.GetAllBookInfoInOrder_1("user"));
        return "";
    }

    @GetMapping("/AllUserOrder")
    public String GetAllUserOrder(Model model)
    {
        model.addAttribute("AllUserOrder", orderService.GerAllUserOrder());
        model.addAttribute("AllUserOrderBook", orderService.GetBookInfoAllOrder());
        return "";
    }

    @GetMapping("/GetUerIdAndStatus_1")
    public String GetUerIdAndStatus_1(HttpServletRequest request, Model model)
    {
        String username = request.getParameter("username");
        model.addAttribute("GetUerIdAndStatus_1", orderService.GetUerIdAndStatus(username, 1));
        return "";
    }

    @GetMapping("/GetUerIdAndStatus_0")
    public String GetUerIdAndStatus_0(HttpServletRequest request, Model model)
    {
        String username = request.getParameter("username");
        model.addAttribute("GetUerIdAndStatus_0", orderService.GetUerIdAndStatus(username, 0));
        return "";
    }

    @PostMapping("/Update")
    public String UpdateOrderInfo(long orderid, Model model)
    {
        String result = orderService.UpdateOrderInfo(orderid);
        JSONUtil jsonUtil = new JSONUtil();
        if(result.equals("修改成功"))
            model.addAttribute("Update", jsonUtil.success(result));
        else model.addAttribute("Update", jsonUtil.fail(result));
        return "";
    }

    public OrderInfo requestToOrderInfo(HttpServletRequest request)
    {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUsername(request.getParameter("username"));
        orderInfo.setOrder_date(request.getParameter("order_date"));
        orderInfo.setBook_id(Long.parseLong(request.getParameter("book_id")));
        orderInfo.setBook_title(request.getParameter("book_title"));
        orderInfo.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        if(request.getParameter("address").equals(""))
            orderInfo.setAddress("无");
        else
            orderInfo.setAddress(request.getParameter("address"));
        orderInfo.setStatus(Integer.parseInt(request.getParameter("status")));
        return orderInfo;
    }
}
