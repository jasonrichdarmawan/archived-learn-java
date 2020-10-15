package com.company.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDataSource {
  public static Connection connection;

  static {
    try {
//      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(
              "jdbc:mysql://localhost:3306/sono", "root", "password");
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
