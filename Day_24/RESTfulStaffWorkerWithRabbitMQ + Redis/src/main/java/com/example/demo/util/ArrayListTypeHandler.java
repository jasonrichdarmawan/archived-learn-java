package com.example.demo.util;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * TODO: incorrect string value? StaffDataAccessService.java use preparedStatement.setObject() and it works fine.
 */
public class ArrayListTypeHandler extends BaseTypeHandler<ArrayList<String>> {
  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, ArrayList<String> stringArrayList, JdbcType jdbcType) throws SQLException {
    StringBuilder stringBuilder = new StringBuilder(stringArrayList.toString());
    preparedStatement.setString(i, stringBuilder.substring(1,stringBuilder.length()-1));
    //    preparedStatement.setObject(i, stringArrayList);
  }

  @Override
  public ArrayList<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return StringToArrayList.toArrayList(resultSet.getString(s));
  }

  @Override
  public ArrayList<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return StringToArrayList.toArrayList(resultSet.getString(i));
  }

  @Override
  public ArrayList<String> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    return StringToArrayList.toArrayList(callableStatement.getString(i));
  }
}
