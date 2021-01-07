package com.example.springsecurityh2.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.springsecurityh2.config.UserAuthorities.*;

public enum UserRoles {
  PILOT(Set.of(AIRCRAFT_FLY, AIRCRAFT_BOARD)),
  PASSENGER(Set.of(AIRCRAFT_BOARD)),
  SKIPPER(Set.of(SHIP_SAIL));

  private final Set<UserAuthorities> authorities;

  UserRoles(Set<UserAuthorities> authorities) {
    this.authorities = authorities;
  }

  public Set<UserAuthorities> getAuthorities() {
    return authorities;
  }

  public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
    Set<SimpleGrantedAuthority> authorities = getAuthorities().stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
            .collect(Collectors.toSet());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
