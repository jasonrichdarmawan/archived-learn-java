package com.example.springsecurityh2.mapper;

import com.example.springsecurityh2.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT user, scopes FROM user WHERE user=#{user} AND password=#{password}")
  UserModel getUser(String user, String password);
}
