package com.bookshop.web;

import com.alibaba.fastjson.JSONObject;
import com.bookshop.service.OrderService;
import com.bookshop.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bookshop.model.Orderinfo;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    JSONUtil jsonUtil = new JSONUtil();
    /*
    创建订单
     */
    @RequestMapping("/addorder")
    public JSONObject addOrder(Orderinfo orderinfo){
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
    public List<Orderinfo> getOrderByUserId(int userid){
        return orderService.getOrderByUserId(userid);
    }
}
