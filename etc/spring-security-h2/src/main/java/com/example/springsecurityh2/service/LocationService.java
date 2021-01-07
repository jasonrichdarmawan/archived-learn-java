package com.example.springsecurityh2.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

  @PreAuthorize("hasAuthority('aircraft.fly')")
  public String moveTo(String location) {
    return "You have moved to " + location;
  }
}
