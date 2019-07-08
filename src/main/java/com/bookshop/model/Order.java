package com.bookshop.model;


public class Order {

  private long orderid;
  private long userid;
  private long listid;
  private String orderaddress;
  private double ordertotal;
  private long state;


  public long getOrderid() {
    return orderid;
  }

  public void setOrderid(long orderid) {
    this.orderid = orderid;
  }


  public long getUserid() {
    return userid;
  }

  public void setUserid(long userid) {
    this.userid = userid;
  }


  public long getListid() {
    return listid;
  }

  public void setListid(long listid) {
    this.listid = listid;
  }


  public String getOrderaddress() {
    return orderaddress;
  }

  public void setOrderaddress(String orderaddress) {
    this.orderaddress = orderaddress;
  }


  public double getOrdertotal() {
    return ordertotal;
  }

  public void setOrdertotal(double ordertotal) {
    this.ordertotal = ordertotal;
  }


  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }

}
