package com.bookshop.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import com.bookshop.model.Orderinfo;
import java.util.List;

@Mapper
@Component
public interface OrderDao {
    /*
    以orderid查询
     */
    @Select("select * from orderinfo where orderid=#{orderid}")
    public Orderinfo getOrderInfoById(long orderid);
    /*
    以userid查询
     */
    @Select("select * from orderinfo where userid = #{userid}")
    public List<Orderinfo> getOrderByUserId(long userid);
    /*
    设置订单为已支付
     */
    @Update("update orderinfo set state = 1 where orderid=#{orderid}")
    public int setstatetoone(long orderid);
    /*
    创建订单
     */
    @Insert("insert into orderinfo(userid,orderaddress,ordertotal,state,createtime,books) value(#{userid},#{orderaddress},#{ordertotal},#{state},#{createtime},#{books})")
    public int addOrder(Orderinfo orderinfo);
    /*
    删除订单
     */
    @Delete("delete from orderinfo where orderid = #{orderid}")
    public int deleteOrderByOrderId(long orderid);

    @Delete("delete from orderinfo where userid = #{userid}")
    public void deleteOrderByUserId(long userid);
}
