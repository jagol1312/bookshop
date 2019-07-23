package com.bookshop.model;


import com.fasterxml.jackson.annotation.JsonFormat;

public class Book {

  private long bookid;
  private String bookname;
  private String isbn;
  private String press;
  private String author;
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  private java.sql.Date pubdate;
  private double price;
  private String bookintroduce;
  private long stock;
  private String type;
  private String picname;


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


  public String getisbn() {
    return isbn;
  }

  public void setisbn(String isbn) {
    this.isbn = isbn;
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


  public String getPicname() {
    return picname;
  }

  public void setPicname(String picname) {
    this.picname = picname;
  }
}
