package com.bookshop.model;


public class User {

  private long userid;
  private String username;
  private String password;
  private String useraddress;
  private String email;
  private long age;
  private long sex;
  private long roleid;


  public long getUserid() {
    return userid;
  }

  @Override
  public String toString() {
    return "User{" +
            "userid=" + userid +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", useraddress='" + useraddress + '\'' +
            ", email='" + email + '\'' +
            ", age=" + age +
            ", sex=" + sex +
            ", roleid=" + roleid +
            '}';
  }

  public void setUserid(long userid) {
    this.userid = userid;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getUseraddress() {
    return useraddress;
  }

  public void setUseraddress(String useraddress) {
    this.useraddress = useraddress;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public long getAge() {
    return age;
  }

  public void setAge(long age) {
    this.age = age;
  }


  public long getSex() {
    return sex;
  }

  public void setSex(long sex) {
    this.sex = sex;
  }


  public long getRoleid() {
    return roleid;
  }

  public void setRoleid(long roleid) {
    this.roleid = roleid;
  }

}
