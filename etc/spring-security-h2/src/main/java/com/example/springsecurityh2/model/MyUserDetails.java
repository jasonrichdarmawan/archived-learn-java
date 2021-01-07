package com.example.springsecurityh2.model;

import com.example.springsecurityh2.config.UserRoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

  private final String username;
  private final String password;
  private final boolean active;
  private final List<GrantedAuthority> authorities;

  public MyUserDetails(UserModel user) {
    this.username = user.getUser();
    this.password = user.getPassword();
    this.active = user.getActive();

    String[] roles = user.getRoles().split(",");
    this.authorities = new ArrayList<>(roles.length);
    for (String role : roles) {
      this.authorities.addAll(UserRoles.valueOf(role).getGrantedAuthorities());
    }


  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return active;
  }
}
