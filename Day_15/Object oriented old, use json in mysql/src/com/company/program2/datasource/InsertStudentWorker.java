package com.company.program2.datasource;

import java.sql.SQLException;
import java.sql.Statement;

public class InsertStudentWorker implements Runnable {
  private Statement statement;
  private String sql;

  InsertStudentWorker(Statement statement, String sql) {
    this.statement = statement;
    this.sql = sql;
  }

  @Override
  public void run() {
    try {
      this.statement.executeUpdate(this.sql);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
