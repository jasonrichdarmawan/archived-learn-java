package com.example.bankaccount.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * constructor User_LoginModel() {}, method setUser_ID(String user_id), method setHashed_PIN(String hashed_pin) is used by RegisterController.
 * method getHashed_PIN() is used by MyBatis builder/User_LoginMapper.xml.
 */
public class User_LoginModel {
  private String User_ID;
  private String PIN;
  private String Hashed_PIN;

  public User_LoginModel() {}

  public User_LoginModel(@JsonProperty("User_ID") String user_id, @JsonProperty("PIN") String pin) {
    User_ID = user_id;
    PIN = pin;
  }

  public String getUser_ID() {
    return User_ID;
  }

  public void setUser_ID(String user_id) {
    User_ID = user_id;
  }

  public String getPIN() {
    return PIN;
  }

  public String getHashed_PIN() {
    return Hashed_PIN;
  }

  public void setHashed_PIN(String hashed_pin) {
    Hashed_PIN = hashed_pin;
  }
}
