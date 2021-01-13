package com.example.websocketsecurityh2.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChannelMapper {
  // see: https://gitlab.com/romain.rinie/h2database/-/issues/200
  @Select("SELECT CONCAT(admins, ISNULL(','||members,'')) AS results FROM channel WHERE id=#{channelId}")
  String getMembers(String channelId);
}
