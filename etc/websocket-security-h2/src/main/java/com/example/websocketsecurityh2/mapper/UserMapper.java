package com.example.websocketsecurityh2.mapper;

import com.example.websocketsecurityh2.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserMapper {
  @Select("SELECT id, user, password, active FROM user WHERE user=#{userName}")
  Optional<UserModel> getUser(String userName);
}
