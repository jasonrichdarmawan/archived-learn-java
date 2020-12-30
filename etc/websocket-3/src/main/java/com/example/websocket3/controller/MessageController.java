package com.example.websocket3.controller;

import com.example.websocket3.model.ReceiveMessageModel;
import com.example.websocket3.model.SendMessageModel;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

  @MessageMapping("{id}")
  public ReceiveMessageModel message(@DestinationVariable(value = "id") String id, @Header(value = "sid") String sessionID, @Payload SendMessageModel message) {
    String from = sessionID; // TODO
    return new ReceiveMessageModel(from, id, message.getAudio());
  }

}
