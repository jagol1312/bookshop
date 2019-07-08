package com.bookshop.dao;

import com.bookshop.model.BookInfo;
import com.bookshop.model.OrderInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface IOrderInfoDao {

    @Insert("insert into orders(username, order_date, book_id, book_title, quantity, address, status, price) VALUES (#{username}, #{order_date}, #{book_id}, #{book_title}, #{quantity}, #{address}, #{status}, #{price})")
    public void AddNew(OrderInfo orderInfo);

    @Select("select * from orders where username = #{username} and book_id = #{book_id} and address = #{address} and status = 0")
    public OrderInfo IsExistedInOrder(OrderInfo orderInfo);

    @Select("select * from orders where order_id = #{order_id}")
    public OrderInfo IsExistedInOrderByOrderId(long order_id);

    @Select("select * from orders where int username = #{username}")
    public List<OrderInfo> GetUerIdAndStatus(String username, int num);

    @Select("select * from orders where username = #{username} and status = 0")
    public List<OrderInfo> GetAllOrderInfo(String username);

    @Select("select * from bookinfo where bookid in (select book_id from orders where username = #{username} and status = 0)")
    public List<BookInfo> GetAllBookInfoInOrder(String username);

    @Select("select * from orders where username = #{username} and status = 1")
    public List<OrderInfo> GetAllOrderInfoStatus_1(String username);

    @Select("select * from bookinfo where bookid in (select book_id from orders where username = #{username} and status = 1)")
    public List<BookInfo> GetAllBookInfoInOrder_1(String username);

    @Update("update orders set price = price + #{price}, order_date = #{order_date}, quantity = quantity + #{quantity} where username = #{username} and book_id = #{book_id} and address = #{address} and status = 0")
    public void AddToExisted(OrderInfo orderInfo);

    @Update("update orders set status = 1 where order_id = #{orderid}")
    public void Buy(long orderid);

    @Update("update orders set username = #{username}, order_date = #{order_date}, book_id = #{book_id}, book_title = #{book_title}, quantity = #{quantity}, address = #{address}, status = #{status}, price = #{price} where order_id = #{order_id}")
    public void UpdateOrderInfo(long order_id);

    @Delete("delete from orders where order_id = #{order_id}")
    public void DeleteOrderInfo(long order_id);

    @Select("select * from orders where status = 1")
    public List<OrderInfo> GetAllUserOrder();

    @Select("select * from bookinfo where bookid in (select book_id from orders where status = 1)")
    public List<BookInfo> GetBookInfoAllOrder();
}
