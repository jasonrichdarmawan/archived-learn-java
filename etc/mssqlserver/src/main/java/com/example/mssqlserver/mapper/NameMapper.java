package com.example.mssqlserver.mapper;

import com.example.mssqlserver.model.NameModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NameMapper {

  @Select("SELECT * FROM name")
  public List<NameModel> selectAll();

  @Insert("INSERT INTO name (name) VALUES (#{name})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  int insert(NameModel nameModel);
}
