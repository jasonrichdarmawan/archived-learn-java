package com.example.websocketsecurityh2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

// see: https://docs.spring.io/spring-security/site/docs/current/reference/html5/#websocket
@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {
  @Override
  protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
    messages
            .nullDestMatcher().authenticated()
            .simpDestMatchers("/app/**").hasRole("USER")
            .simpDestMatchers("/topic/user/*", "/topic/channel/*").hasRole("USER")
            .simpTypeMatchers(SimpMessageType.MESSAGE, SimpMessageType.SUBSCRIBE).denyAll()
            .anyMessage().denyAll();
  }
}
