package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {
  // Resource server depends on the authorization server to make authorization decision.
  // Disable resource server before using this.
  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails ria = User.withDefaultPasswordEncoder()
            .username("ria")
            .password("password")
            .roles("USER")
            .build();
    UserDetails josh = User.withDefaultPasswordEncoder()
            .username("josh")
            .password("password")
            .roles("USER")
            .build();
    return new InMemoryUserDetailsManager(ria, josh);
  }
}
