package com.example.websocketsecurityh2.model;

import java.util.Date;

public class MessageModel {
  public final String user;
  public final String text;
  public final String iat = new Date().toString();;

  public MessageModel(String user, String text) {
    this.user = user;
    this.text = text;
  }
}
