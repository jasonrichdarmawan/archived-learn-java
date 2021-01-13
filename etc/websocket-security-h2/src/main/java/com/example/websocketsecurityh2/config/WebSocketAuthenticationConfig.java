package com.example.websocketsecurityh2.config;

import com.example.websocketsecurityh2.mapper.ChannelMapper;
import com.example.websocketsecurityh2.model.MyUserDetails;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketAuthenticationConfig implements WebSocketMessageBrokerConfigurer {
  private static final String TOPIC_CHANNEL = "/topic/channel/";

  private final ChannelMapper channelMapper;

  public WebSocketAuthenticationConfig(ChannelMapper channelMapper) {
    this.channelMapper = channelMapper;
  }

  @Override
  public void configureClientInboundChannel(ChannelRegistration registration) {
    registration.interceptors(new ChannelInterceptor() {
      @Override
      public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        assert accessor != null;

        if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
          String destination = accessor.getDestination();
          assert destination != null;

          if (destination.startsWith(TOPIC_CHANNEL)) {
            String userId = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
            String channelId = destination.substring(TOPIC_CHANNEL.length());
            String[] members = channelMapper.getMembers(channelId).split(",");

            for (String member : members) {
              if (member.equals(userId)) {
                return message;
              }
            }

            // TODO: return StompCommand.Error
            // see: https://stackoverflow.com/questions/33741511/how-to-send-error-message-to-stomp-clients-with-spring-websocket
            return null;
          }

        }

        return message;
      }
    });
  }
}
