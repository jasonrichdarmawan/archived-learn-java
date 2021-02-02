package com.example.websocketsecurityh2.controller;

import com.example.websocketsecurityh2.model.MyUserDetails;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDetailsController {
  // TODO: hide password.
  @GetMapping("/userdetails")
  public MyUserDetails getUserDetails(@CurrentSecurityContext(expression = "authentication.principal") Object user) {
    return (MyUserDetails) user;
  }

  @GetMapping("/helloworld")
  public String getHelloWorld() {
    return "Hello World";
  }
}
