package com.bookshop.model;


public class Cart {

  private long cartid;
  private long userid;
  private long bookid;
  private long cartquantity;
  private double carttotal;


  public long getCartid() {
    return cartid;
  }

  public void setCartid(long cartid) {
    this.cartid = cartid;
  }


  public long getUserid() {
    return userid;
  }

  public void setUserid(long userid) {
    this.userid = userid;
  }


  public long getBookid() {
    return bookid;
  }

  public void setBookid(long bookid) {
    this.bookid = bookid;
  }


  public long getCartquantity() {
    return cartquantity;
  }

  public void setCartquantity(long cartquantity) {
    this.cartquantity = cartquantity;
  }


  public double getCarttotal() {
    return carttotal;
  }

  public void setCarttotal(double carttotal) {
    this.carttotal = carttotal;
  }

}
