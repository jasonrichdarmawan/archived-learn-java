package com.example.springsecurityh2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  // fix: Whitelabel Error Page /h2-console
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers("/h2-console/**").permitAll();

    http.csrf().disable();
    http.headers().frameOptions().disable();
  }
}
