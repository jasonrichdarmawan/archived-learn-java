package com.example.websocketsecurityh2.model;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class MyUserDetails implements UserDetails, CredentialsContainer {

  private final String userId;

  private final String username;
  private String password;
  private final boolean active;
  private final Set<GrantedAuthority> authorities;

  public MyUserDetails(UserModel user) {
    this.userId = user.getId();

    this.username = user.getUser();
    this.password = user.getPassword();
    this.active = user.getActive();

    this.authorities = Set.of(new SimpleGrantedAuthority("ROLE_USER"));
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

  public String getUserId() {
    return userId;
  }

  @Override
  public void eraseCredentials() {
    this.password = null;
  }
}
