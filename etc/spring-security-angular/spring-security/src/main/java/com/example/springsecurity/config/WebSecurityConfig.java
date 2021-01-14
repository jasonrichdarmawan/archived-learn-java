package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .cors(withDefaults())
            .httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers("/user").permitAll()
            .anyRequest().authenticated();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    // fix: GET /resource blocked by CORS policy on the Browser.
    configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));

    // fix: GET /user can't have Request Header with key: Authorization.
    configuration.setAllowedHeaders(Collections.singletonList("authorization"));

    // fix: GET /user without OPTIONS withCredentials: true can't Set-Cookie on the Browser.
    configuration.setAllowCredentials(true);

    configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
