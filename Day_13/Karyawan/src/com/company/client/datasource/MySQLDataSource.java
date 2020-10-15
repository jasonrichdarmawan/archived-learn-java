package com.company.client.datasource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLDataSource {
  public static Connection connection = null;

  public static void connect(String DATABASE_HOSTNAME, int DATABASE_PORT, String DATABASE_SCHEMA, String DATABASE_USER, String DATABASE_PASSWORD) {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      String DATABASE_URL = String.format("jdbc:mysql://%s:%d/%s", DATABASE_HOSTNAME, DATABASE_PORT, DATABASE_SCHEMA);
      connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public static void updateKaryawanById(int id, String nama, float gajiPokok, int absensi, int jumlahCuti, int statusKaryawan) {
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement("UPDATE karyawan SET Nama=?, GajiPokok=?, JmlAbsensi=?, JmlCutiTerpakai=?, StatusKaryawan=? WHERE id=?");
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    try {
      preparedStatement.setString(1, nama);
      preparedStatement.setFloat(2, gajiPokok);
      preparedStatement.setInt(3, absensi);
      preparedStatement.setInt(4, jumlahCuti);
      preparedStatement.setInt(5, statusKaryawan);
      preparedStatement.setInt(6, id);
      preparedStatement.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
