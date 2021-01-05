package com.example.authorizationserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.crypto.keys.KeyManager;
import org.springframework.security.crypto.keys.StaticKeyGeneratingKeyManager;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.util.UUID;

@EnableWebSecurity
@Import(OAuth2AuthorizationServerConfiguration.class)
public class AuthorizationServerConfig {

  @Bean
  public RegisteredClientRepository registeredClientRepository() {
    RegisteredClient pilotClient = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId("pilot-client")
            .clientSecret("secret")
            .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
            .scope("aircraft.fly")
            .scope("aircraft.board")
            .build();
    RegisteredClient passengerClient = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId("passenger-client")
            .clientSecret("secret")
            .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
            .scope("aircraft.board")
            .build();
    RegisteredClient sailorClient = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId("sailor-client")
            .clientSecret("secret")
            .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
            .scope("boat.sail")
            .scope("boat.board")
            .build();
    return new InMemoryRegisteredClientRepository(pilotClient, passengerClient, sailorClient);
  }

  @Bean
  public KeyManager keyManager() {
    return new StaticKeyGeneratingKeyManager();
  }
}
