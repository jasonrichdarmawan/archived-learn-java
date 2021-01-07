package com.example.springsecurityh2.service;

import com.example.springsecurityh2.mapper.UserMapper;
import com.example.springsecurityh2.model.MyUserDetails;
import com.example.springsecurityh2.model.UserModel;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

  private final UserMapper userMapper;

  public MyUserDetailsService(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    Optional<UserModel> user = userMapper.getUser(s);

    user.orElseThrow(() -> new UsernameNotFoundException("Username Not Found: " + s)); // TODO: who receives the Exception?

    return user.map(MyUserDetails::new).get();
  }
}
