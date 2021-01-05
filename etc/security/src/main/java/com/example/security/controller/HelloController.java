package com.example.security.controller;

import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/")
  public String getHello(@CurrentSecurityContext(expression = "authentication.name") String name) {
    return "Hello " + name;
  }
}
