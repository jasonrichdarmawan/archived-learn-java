package com.example.websocketsecurityh2.controller;

import com.example.websocketsecurityh2.mapper.ChannelMapper;
import com.example.websocketsecurityh2.model.MessageModel;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;

import java.util.Arrays;

@Controller
public class ChannelController {

  private final ChannelMapper channelMapper;

  public ChannelController(ChannelMapper channelMapper) {
    this.channelMapper = channelMapper;
  }

  @MessageMapping("/channel/{channelId}")
  @SendTo("/topic/channel/{channelId}")
  public MessageModel sendMessage(@DestinationVariable String channelId, MessageModel message) {
    // TODO: Prevent unauthorized user to SUBSCRIBE and SEND message.
    //  Temporary fix.
    //  in case of performance issue, consider to use:
    //  1. in-memory database.
    //  2. use SecurityContextHolder.getContext().getAuthentication().getPrincipal()
    String[] members = channelMapper.getMembers(channelId).split(",");
    for (String member : members) {
      if (member.equals(message.getUserId())) {
        return message;
      }
    }
    throw new AccessDeniedException("Did not match members list");
  }

}
