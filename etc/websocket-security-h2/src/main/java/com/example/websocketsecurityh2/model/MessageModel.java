package com.example.websocketsecurityh2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

public class MessageModel {
  private final String userId;
  private final String text;
  private final String iat = new Date().toString();

  public MessageModel(@JsonProperty("text") String text) {
    this.userId = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
    this.text = text;
  }

  public String getUserId() {
    return userId;
  }

  public String getText() {
    return text;
  }

  public String getIat() {
    return iat;
  }
}
