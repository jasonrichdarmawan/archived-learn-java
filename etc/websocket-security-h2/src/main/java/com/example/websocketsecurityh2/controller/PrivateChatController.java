package com.example.websocketsecurityh2.controller;

import com.example.websocketsecurityh2.model.MessageModel;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class PrivateChatController {

  private final SimpMessagingTemplate messaging;

  public PrivateChatController(SimpMessagingTemplate messaging) {
    this.messaging = messaging;
  }

  @MessageMapping("/user/{userName}")
  public void sendMessage(@DestinationVariable String userName, MessageModel message) {
    messaging.convertAndSendToUser(userName, "/queue/messages", message);
  }

}
