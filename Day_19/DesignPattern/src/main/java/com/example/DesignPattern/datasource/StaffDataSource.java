package com.example.designpattern.datasource;

import java.sql.*;

/**
 * @deprecated use HikariCP instead.
 */
public class StaffDataSource {
  private Statement statement;
  private Connection connection;

  public StaffDataSource(String api, String database, String hostname, int port, String name, String user, String password) {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    try {
      String url = String.format("%s:%s://%s:%d/%s?user=%s&password=%s", api, database, hostname, port, name, user, password);
      this.connection = DriverManager.getConnection(url);
      this.statement = this.connection.createStatement();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public void executeUpdate(String sql) {
    try {
      this.statement.executeUpdate(sql);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public ResultSet executeQuery(String sql) {
    try {
      return this.statement.executeQuery(sql);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    return null;
  }

  public void close() {
    try {
      this.statement.close();
      this.connection.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
