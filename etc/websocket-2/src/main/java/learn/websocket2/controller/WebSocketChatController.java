package learn.websocket2.controller;

import learn.websocket2.domain.WebSocketChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketChatController {

  @MessageMapping("/chat.sendMessage")
  @SendTo("/topic/javainuse")
  public WebSocketChatMessage sendMessage(@Payload WebSocketChatMessage message) {
    return message;
  }

  @MessageMapping("/chat.newUser")
  @SendTo("/topic/javainuse")
  public WebSocketChatMessage newUser(@Payload WebSocketChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
    headerAccessor.getSessionAttributes().put("username", message.getSender()); // dependency of WebSocketChatEventListener to handle SessionDisconnectEvent.
    return message;
  }
}
