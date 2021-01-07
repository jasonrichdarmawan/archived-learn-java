package com.example.springsecurityh2.config;

public enum UserAuthorities {
  AIRCRAFT_FLY("aircraft:fly"),
  AIRCRAFT_BOARD("aircraft:board"),
  SHIP_SAIL("ship:sail");

  private final String permission;

  UserAuthorities(String permission) {
    this.permission = permission;
  }

  public String getPermission() {
    return permission;
  }
}
