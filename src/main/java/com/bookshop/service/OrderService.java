package com.bookshop.service;

import com.bookshop.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookshop.model.Orderinfo;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    /*
    创建订单
     */
    public int addOrder(Orderinfo orderinfo){
        orderinfo.setState(0);
        return orderDao.addOrder(orderinfo);
    }
    /*
    设置订单为已支付
     */
    public int setstatetoone(long orderid){
        return orderDao.setstatetoone(orderid);
    }
    /*
    以orderid查询订单
     */
    public Orderinfo getOrderInfoById(long orderid){
        return orderDao.getOrderInfoById(orderid);
    }
    /*
    以userid查询订单
     */
    public List<Orderinfo> getOrderByUserId(int userid){
       return orderDao.getOrderByUserId(userid);
    }
    /*
    删除订单
     */
    public void deleteorderbyuserid(long userid){
         orderDao.deleteOrderByUserId(userid);
    }
}
