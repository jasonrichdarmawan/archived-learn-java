package com.example.springsecurityh2.service;

import org.springframework.stereotype.Service;

@Service
public class LocationService {

  public String moveTo(String location) {
    return "You have moved to " + location;
  }
}
