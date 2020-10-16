package com.nexsoft.models;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class User {
  private Statement stmt;
  private Connection conn = null;

  public User() {
    try {
      String url = "jdbc:mysql://localhost:3306/day14?user=root&password=password";



      this.conn = DriverManager.getConnection(url);

      this.stmt = this.conn.createStatement();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public ResultSet getUsers() {
    try {
      ResultSet result = this.stmt.executeQuery("SELECT * FROM users");

      return result;
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  public void insert(String table, Map data) throws SQLException {
    String fields = "";
    String insert = "";

    for (Object key : data.keySet()) {
      fields += key + ",";
      insert += "'" + data.get(key) + "',";
    }

    fields = fields.substring(0, fields.length() - 1);
    insert = insert.substring(0, insert.length() - 1);

    try {
      Statement stmt = this.conn.createStatement();

      stmt.executeUpdate("INSERT INTO " + table + "(" + fields + ") VALUES(" + insert + ")");

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public ResultSet get_where(String table, Map where) throws SQLException {
    String whereString = "";

    for (Object key : where.keySet()) {
      whereString += key + "='" + where.get(key) + "' AND ";

    }

    whereString = whereString.substring(0, whereString.length() - 4);

    try {
      Statement stmt = this.conn.createStatement();
      ResultSet result = stmt.executeQuery("SELECT * FROM " + table + " WHERE " + whereString);

      return result;

    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  public void update(String table, Map data, Map where) throws SQLException {
    String setString = "";
    String whereString = "";

    for (Object key : data.keySet()) {
      setString += key + "='" + data.get(key) + "',";
      // useranme='abcde',... ,
    }

    for (Object key : where.keySet()) {
      whereString += key + "='" + where.get(key) + "' AND ";
    }

    setString = setString.substring(0, setString.length() - 1); // username='abcde', => username='abcde'
    whereString = whereString.substring(0, whereString.length() - 5);

    try {
      Statement stmt = this.conn.createStatement();

      stmt.executeUpdate("UPDATE " + table + " SET " + setString + " WHERE " + whereString);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void delete(String table, Map where) throws SQLException {
    String whereString = "";

    for (Object key : where.keySet()) {
      whereString += key + "='" + where.get(key) + "' AND ";
    }

    whereString = whereString.substring(0, whereString.length() - 5);

    try {
      Statement stmt = this.conn.createStatement();

      stmt.executeUpdate("DELETE FROM " + table + " WHERE " + whereString);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
