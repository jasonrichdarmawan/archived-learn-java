package com.example.springsecurityh2.mapper;

import com.example.springsecurityh2.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserMapper {

  @Select("SELECT user, password, active, roles FROM user WHERE user=#{user}")
  Optional<UserModel> getUser(String user);
}
