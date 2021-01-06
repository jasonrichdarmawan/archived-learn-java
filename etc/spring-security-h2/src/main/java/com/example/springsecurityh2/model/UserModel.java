package com.example.springsecurityh2.model;

public class UserModel {
  private String user;
  private String password;
  private boolean active;
  private String roles;

  public String getUser() {
    return user;
  }

  public String getPassword() {
    return password;
  }

  public boolean getActive() {
    return active;
  }

  public String getRoles() {
    return roles;
  }
}
