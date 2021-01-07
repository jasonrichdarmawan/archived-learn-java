package com.example.springsecurityh2.controller;

import com.example.springsecurityh2.service.LocationService;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {

  private final LocationService locationService;

  public HelloController(LocationService locationService) {
    this.locationService = locationService;
  }

  @GetMapping("/")
  public String getName(@CurrentSecurityContext(expression = "authentication.name") String name) {
    return "Hello " + name;
  }

  @PostMapping("/location")
  public String changeLocation(@RequestBody String location) {
    return locationService.moveTo(location);
  }
}
