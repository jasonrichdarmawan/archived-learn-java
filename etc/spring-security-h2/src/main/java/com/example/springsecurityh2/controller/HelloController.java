package com.example.springsecurityh2.controller;

import com.example.springsecurityh2.mapper.UserMapper;
import com.example.springsecurityh2.model.UserModel;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  private final UserMapper userMapper;

  public HelloController(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @GetMapping("/")
  public String getName(@CurrentSecurityContext(expression = "authentication.name") String name) {
    UserModel user = userMapper.getUser("pilot-client", "secret");
    return "Hello " + user.getScopes();
  }
}
