package com.nexsoft.models;

import java.sql.*;
import java.util.regex.Pattern;

public class Authenticator {
  private Statement stmt;
  private ResultSet user = null;

  public Authenticator() {
    try {
      String url = "jdbc:mysql://localhost:3306/day14?user=root&password=password";

      DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

      Connection conn = DriverManager.getConnection(url);

      this.stmt = conn.createStatement();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public boolean authenticate(String username, String password) {
    try {
      ResultSet user = this.stmt.executeQuery("SELECT * FROM users WHERE username='" + username + "'");

      if (user.next()) {
        Boolean passwordCheck = Pattern.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,}$", password);

        if (passwordCheck) {
          this.user = user;
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return false;
  }

  public ResultSet getUser() {
    return this.user;
  }
}

