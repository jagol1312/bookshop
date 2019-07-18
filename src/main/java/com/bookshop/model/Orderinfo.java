package com.bookshop.model;


public class Orderinfo {

  private long orderid;
  private long userid;
  private String orderaddress;
  private double ordertotal;
  private long state;
  private java.sql.Date createtime;
  private String books;


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


  public java.sql.Date getCreatetime() {
    return createtime;
  }

  public void setCreatetime(java.sql.Date createtime) {
    this.createtime = createtime;
  }


  public String getBooks() {
    return books;
  }

  public void setBooks(String books) {
    this.books = books;
  }

}
