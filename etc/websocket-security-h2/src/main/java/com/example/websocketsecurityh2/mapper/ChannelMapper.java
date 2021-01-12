package com.example.websocketsecurityh2.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChannelMapper {
  @Select("SELECT members FROM channel WHERE id=#{channelId}")
  String getMembers(String channelId);
}
