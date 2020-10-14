package com.company.datasource;

import java.io.IOException;
import java.sql.*;

import static com.company.MainUtil.bufferedReader;

public class MySQLDataSource {
  public static Connection connection = null;

  public static void connect() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(
              "jdbc:mysql://localhost:3306/sono", "root", "password");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public static void readTableEMP() {
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM emp");
      while (resultSet.next())
        System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public static void insertRow() {
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement("INSERT INTO emp (name, age) VALUES (?,?)");
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    String name = "";
    int age = 0;
    try {
      System.out.print("Enter the name: ");
      name = bufferedReader.readLine();

      System.out.print("Enter the age: ");
      age = Integer.parseInt(bufferedReader.readLine());
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      preparedStatement.setString(1, name);
      preparedStatement.setInt(2, age);
      preparedStatement.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public static void updateRow() {
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement("UPDATE emp SET name=?, age=? WHERE id=?");
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    int ID = 0;
    String newName = "";
    int newAge = 0;
    try {
      System.out.print("Enter the ID: ");
      ID = Integer.parseInt(bufferedReader.readLine());
      System.out.print("Enter the new name: ");
      newName = bufferedReader.readLine();
      System.out.print("Enter the new age: ");
      newAge = Integer.parseInt(bufferedReader.readLine());
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      preparedStatement.setString(1, newName);
      preparedStatement.setInt(2, newAge);
      preparedStatement.setInt(3, ID);
      preparedStatement.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public static void deleteRow() {
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement("DELETE FROM emp WHERE ID=?");
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    int ID = 0;
    try {
      System.out.print("Enter the ID: ");
      ID = Integer.parseInt(bufferedReader.readLine());
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      preparedStatement.setInt(1, ID);
      preparedStatement.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
