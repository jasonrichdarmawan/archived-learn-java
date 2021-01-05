package com.example.security.controller;

import com.example.security.service.LocationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  private final LocationService locationService;

  // @Autowired vs constructor injection
  // reference: https://stackoverflow.com/questions/40620000/spring-autowire-on-properties-vs-constructor
  public HelloController(LocationService locationService) {
    this.locationService = locationService;
  }

  @GetMapping("/")
  public String getHello(@CurrentSecurityContext(expression = "authentication.name") String name) {
    return "Hello " + name;
  }

  @PostMapping("/location")
  public String changeLocation(Authentication authentication, @RequestBody String location) {
//    // see SecurityConfig.java customJwtAuthenticationConverter
//    authentication.getAuthorities().add(new SimpleGrantedAuthority("captain"));
    return this.locationService.moveTo(location);
  }
}
