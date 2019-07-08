package com.bookshop.model;


public class Book {

  private long bookid;
  private String bookname;
  private String isdn;
  private String press;
  private String author;
  private java.sql.Date pubdate;
  private double price;
  private String bookintroduce;
  private long stock;
  private String type;


  public long getBookid() {
    return bookid;
  }

  public void setBookid(long bookid) {
    this.bookid = bookid;
  }


  public String getBookname() {
    return bookname;
  }

  public void setBookname(String bookname) {
    this.bookname = bookname;
  }


  public String getIsdn() {
    return isdn;
  }

  public void setIsdn(String isdn) {
    this.isdn = isdn;
  }


  public String getPress() {
    return press;
  }

  public void setPress(String press) {
    this.press = press;
  }


  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }


  public java.sql.Date getPubdate() {
    return pubdate;
  }

  public void setPubdate(java.sql.Date pubdate) {
    this.pubdate = pubdate;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public String getBookintroduce() {
    return bookintroduce;
  }

  public void setBookintroduce(String bookintroduce) {
    this.bookintroduce = bookintroduce;
  }


  public long getStock() {
    return stock;
  }

  public void setStock(long stock) {
    this.stock = stock;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
