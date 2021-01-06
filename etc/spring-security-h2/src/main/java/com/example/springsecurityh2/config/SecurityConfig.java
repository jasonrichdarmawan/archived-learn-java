package com.example.springsecurityh2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  // fix: Whitelabel Error Page /h2-console
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/h2-console/**");
  }
}
