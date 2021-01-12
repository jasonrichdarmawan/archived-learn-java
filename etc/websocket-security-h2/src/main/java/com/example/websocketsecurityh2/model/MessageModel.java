package com.example.websocketsecurityh2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

// The public final attribute is intentional.
// Learn more about Encapsulation.
public class MessageModel {
  public final String userId;
  public final String text;
  public final String iat = new Date().toString();

  public MessageModel(@JsonProperty("text") String text) {
    this.userId = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
    this.text = text;
  }
}
