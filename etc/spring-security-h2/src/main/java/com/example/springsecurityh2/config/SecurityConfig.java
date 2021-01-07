package com.example.springsecurityh2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.springsecurityh2.config.UserAuthorities.AIRCRAFT_BOARD;
import static com.example.springsecurityh2.config.UserAuthorities.AIRCRAFT_FLY;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;

  public SecurityConfig(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  // fix: Whitelabel Error Page /h2-console
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/h2-console/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable() // TODO: implement csrf.
            .authorizeRequests()
            .antMatchers("/api/v1/hello/").hasAuthority(AIRCRAFT_BOARD.getAuthority()) // pilot-client, deadhead-client, passenger-client
            .antMatchers("/api/v1/hello/location").hasAuthority(AIRCRAFT_FLY.getAuthority()) // pilot-client, deadhead-client
            .anyRequest().authenticated()
            .and()
            .httpBasic();
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder(10);
  }
}
