package com.example.bankaccount.model;

public class User_InfoModel {
  private String User_ID;
  private String Account_Number;
  private String Full_Name;
  private int ISO_4217;

  public String getUser_ID() {
    return User_ID;
  }

  public void setUser_ID(String user_ID) {
    User_ID = user_ID;
  }

  public String getAccount_Number() {
    return Account_Number;
  }

  public String getFull_Name() {
    return Full_Name;
  }

  public void setFull_Name(String full_Name) {
    Full_Name = full_Name;
  }

  public int getISO_4217() {
    return ISO_4217;
  }

  public void setISO_4217(int ISO_4217) {
    this.ISO_4217 = ISO_4217;
  }
}
