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

  // known bug caused by curl:
  // if user do `curl localhost:8080/location -i -H "Authorization: Bearer tokenHere" --data "Seattle"
  // the request header Content-Type's value is Content-Type: application/x-www-form-urlencoded
  // This cause issue, e.g if user send location "Seattle", the returned value would be "Seattle="
  // The fix is to make sure the request header Content Type's value is Content-Type: text/plain
  // reference: https://stackoverflow.com/a/43056956/13285583
  @PostMapping("/location")
  public String changeLocation(Authentication authentication, @RequestBody String location) {
//    // see SecurityConfig.java customJwtAuthenticationConverter
//    authentication.getAuthorities().add(new SimpleGrantedAuthority("captain"));
    return this.locationService.moveTo(location);
  }
}
