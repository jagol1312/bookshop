package com.bookshop.web;

import com.alibaba.fastjson.JSONObject;
import com.bookshop.model.User;
import com.bookshop.service.CartService;
import com.bookshop.service.OrderService;
import com.bookshop.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bookshop.model.Orderinfo;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    JSONUtil jsonUtil = new JSONUtil();
    /*
    创建订单
     */
    @RequestMapping("/addorder")
    public JSONObject addOrder(HttpServletRequest request, Orderinfo orderinfo){
        long userid = ((User) request.getSession().getAttribute("user")).getUserid();
        orderinfo.setUserid(userid);
        StringBuffer s = new StringBuffer();
        List<Map> list = cartService.getcartbooksname(userid);
        for(int i = 0; i < list.size(); i++) {
            Map map = list.get(i);
            Set set = map.keySet();
            Iterator it = set.iterator();
            while(it.hasNext()) {
                s.append(map.get(it.next())+" ");
            }
        }
        orderinfo.setBooks(s.toString());

        if(orderService.addOrder(orderinfo)>0)
            return jsonUtil.success("创建订单成功");
        else
            return jsonUtil.fail("创建订单失败");
    }
    /*
    设置订单为已支付
     */
    @RequestMapping("/setstatetoone")
    public JSONObject setstatetoone(long orderid){
        if(orderService.setstatetoone(orderid)>0)
            return jsonUtil.success("支付成功");
        else
            return jsonUtil.fail("支付失败");
    }
    /*
    以orderid查询
     */
    @RequestMapping("/getorderbyorderid")
    public Orderinfo getOrderInfoById(long orderid){
        return orderService.getOrderInfoById(orderid);
    }
    /*
    以userid查询
     */
    @RequestMapping("/getorderbyuserid")
    public List<Map> getOrderByUserId(HttpServletRequest request){
        long userid = ((User) request.getSession().getAttribute("user")).getUserid();
        return orderService.getOrderByUserId(userid);
    }
    @RequestMapping("/getorders")
    public List<Orderinfo> getorders(){
        return orderService.getorders();
    }
}
