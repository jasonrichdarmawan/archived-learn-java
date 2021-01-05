package com.example.security.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LocationService {

  // Option 1: Business Logic inside the method.
  public String moveTo(String location) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
    if (authorities.contains("captain")) {
      return "You have moved to " + location;
    }
    throw new AccessDeniedException("You will not move today");
  }

//  // Option 2: Remove the Business Logic from the method.
//  // require @EnableGlobalMethodSecurity(prePostEnabled = true) in SecurityConfig.java
//  @PreAuthorize("hasAuthority('captain')")
//  public String moveTo(String location) {
//    return "You have moved to " + location;
//  }

//  // Option 3: handle it at Request Level.
//  // see Overridden method configure(HttpSecurity http)
//  public String moveTo(String location) {
//    return "You have moved to " + location;
//  }
}
