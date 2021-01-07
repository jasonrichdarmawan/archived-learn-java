package com.example.springsecurityh2.config;

public enum UserAuthorities {
  AIRCRAFT_FLY("aircraft:fly"),
  AIRCRAFT_BOARD("aircraft:board"),
  SHIP_SAIL("ship:sail");

  private final String authority;

  UserAuthorities(String authority) {
    this.authority = authority;
  }

  public String getAuthority() {
    return authority;
  }
}
