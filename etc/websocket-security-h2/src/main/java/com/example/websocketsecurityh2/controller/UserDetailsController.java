package com.example.websocketsecurityh2.controller;

import com.example.websocketsecurityh2.model.MyUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserDetailsController {
  // TODO: hide password.
  // note: The Principal object by default does not store password.
  // Therefore, you should not need to use `userDetails.clearCredentials()` manually.
  // see: https://github.com/kidfrom/learn-java/tree/main/etc/spring-security-angular/spring-security
  @GetMapping("/userdetails")
  public MyUserDetails getUserDetails(@CurrentSecurityContext(expression = "authentication.principal") Object principal) {
    MyUserDetails userDetails = (MyUserDetails) principal;
    userDetails.clearCredentials();
    return userDetails;
  }

  @GetMapping("/helloworld")
  public String getHelloWorld() {
    return "Hello World";
  }
}
