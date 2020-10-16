package com.company.program2.datasource;

import java.sql.*;

public class StudentDataSource {
  private Statement statement;
  private Connection connection;

  public StudentDataSource(String api, String database, String hostname, int port, String name, String user, String password) {
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

  public void insertStudent(String sql) {
    InsertStudentWorker insertStudentWorker = new InsertStudentWorker(this.statement, sql);
    Thread thread = new Thread(insertStudentWorker);
    thread.start();
    try {
      thread.join(); // waits for this thread to die. guarantee to execute the thread in order.
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public ResultSet selectAllStudents() {
    try {
      return this.statement.executeQuery("SELECT * FROM studentsnew");
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    return null;
  }

  public void updateStudent(String sql) {
    try {
      this.statement.executeUpdate(sql);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public void deleteStudentById(int id) {
    try {
      String sql = String.format("DELETE FROM studentsnew WHERE id=%d", id);
      this.statement.executeUpdate(sql);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
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
