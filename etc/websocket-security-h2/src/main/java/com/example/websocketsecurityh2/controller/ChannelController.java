package com.example.websocketsecurityh2.controller;

import com.example.websocketsecurityh2.model.MessageModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChannelController {

  @MessageMapping("/channel/{id}")
  @SendTo("/topic/channel/{id}")
  public MessageModel sendMessage(MessageModel message) {
    return message;
  }

}
