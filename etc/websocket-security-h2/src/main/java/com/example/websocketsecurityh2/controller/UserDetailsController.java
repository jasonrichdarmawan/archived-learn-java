package com.example.websocketsecurityh2.controller;

import com.example.websocketsecurityh2.model.MyUserDetails;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDetailsController {
  // note: The Principal object by default does not store password.
  // see for example: https://github.com/kidfrom/learn-java/tree/main/etc/spring-security-angular/spring-security
  // To fix this, try to implements CredentialsContainer to the UserDetails implementation.
  // see for reference: https://stackoverflow.com/questions/31630818/spring-security-custom-authentication-authenticationprovider-vs-userdetailsser
  @GetMapping("/userdetails")
  public MyUserDetails getUserDetails(@CurrentSecurityContext(expression = "authentication.principal") Object principal) {
    return (MyUserDetails) principal;
  }

  @GetMapping("/helloworld")
  public String getHelloWorld() {
    return "Hello World";
  }
}
