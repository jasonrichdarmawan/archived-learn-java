package com.example.mssqlserver.mapper;

import com.example.mssqlserver.model.NameModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NameMapper {

  @Select("SELECT * FROM name")
  public List<NameModel> selectAll();

  @SelectKey(statement = "SELECT NEWID()", keyProperty = "newid", resultType = String.class, before = true)
  @Insert("INSERT INTO name (name, newid) VALUES (#{name}, #{newid})")
//  @Options(useGeneratedKeys = true, keyProperty = "id")
  int insert(NameModel nameModel);
}
