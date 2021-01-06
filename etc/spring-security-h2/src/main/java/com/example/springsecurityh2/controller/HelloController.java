package com.example.springsecurityh2.controller;

import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  
  @GetMapping("/")
  public String getName(@CurrentSecurityContext(expression = "authentication.name") String name) {
    return "Hello " + name;
  }
}
