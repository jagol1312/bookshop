package com.bookshop.service;

import com.bookshop.dao.IOrderInfoDao;
import com.bookshop.model.BookInfo;
import com.bookshop.model.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private IOrderInfoDao dao;

    public String AddToOrder(OrderInfo orderInfo) {
        if(dao.IsExistedInOrder(orderInfo) == null)
            dao.AddNew(orderInfo);
        else
            dao.AddToExisted(orderInfo);
        return "添加成功！";
    }

    public String UpdateOrderInfo(long orderid) {
        if(dao.IsExistedInOrderByOrderId(orderid) == null) return "修改失败， 该项不存在！";
        dao.UpdateOrderInfo(orderid);
        return "修改成功！";
    }

    public String DeleteOrderInfo(long orderid) {
        if(dao.IsExistedInOrderByOrderId(orderid) == null) return "删除失败， 该项不存在！";
        dao.DeleteOrderInfo(orderid);
        return "删除成功！";
    }

    public List<OrderInfo> GetUerIdAndStatus(String username, int num) {
        return this.dao.GetUerIdAndStatus(username, num);
    }

    public List<OrderInfo> GetAllOrderInfo(String username){
        return this.dao.GetAllOrderInfo(username);
    }

    public List<BookInfo> GetAllBookInfoInOrder(String username) {
        return this.dao.GetAllBookInfoInOrder(username);
    }

    public String Buy(long orderid) {
        if(dao.IsExistedInOrderByOrderId(orderid) == null) return "购买失败， 该项不存在！";
        dao.Buy(orderid);
        return "购买成功！";
    }

    public List<OrderInfo> GetAllOrderInfoStatus_1(String username) {
        return this.dao.GetAllOrderInfoStatus_1(username);
    }

    public List<BookInfo> GetAllBookInfoInOrder_1(String username) {
        return this.dao.GetAllBookInfoInOrder_1(username);
    }

    public List<OrderInfo> GerAllUserOrder() {
        return this.dao.GetAllUserOrder();
    }

    public List<BookInfo> GetBookInfoAllOrder() {
        return this.dao.GetBookInfoAllOrder();
    }
}
