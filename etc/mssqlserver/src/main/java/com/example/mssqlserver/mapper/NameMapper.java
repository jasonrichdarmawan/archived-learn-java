package com.example.mssqlserver.mapper;

import com.example.mssqlserver.model.NameModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NameMapper {

  @Select("SELECT * FROM name")
  public List<NameModel> selectAll();
}
