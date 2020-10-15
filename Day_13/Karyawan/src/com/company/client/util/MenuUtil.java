package com.company.client.util;

import com.company.client.Main;
import com.company.client.Socket.SocketUtil;
import com.company.client.datasource.MySQLDataSource;
import com.company.client.model.Staff;
import com.company.common.PropertiesUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MenuUtil {
  public static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
  public static List<Staff> staffs = new ArrayList<>();

  public static void printPreMenu() {
    MainUtil.clearTerminal();
    System.out.println("PRE-MENU");
    System.out.println("80. Import Konfigurasi config.properties");
    System.out.println("81. Connect to Socket Server to Get Data langsung masukin ke DB");
  }

  public static void showPreMenu() {
    boolean isExit = false;

    while (!isExit) {
      MainUtil.pressAnyKeyToContinue(bufferedReader);

      printPreMenu();

      String menuNumber = "";

      try {
        System.out.print("Enter the menu's number: ");

        menuNumber = bufferedReader.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }

      switch (menuNumber) {
        case "80":
          Main.properties = PropertiesUtil.load("config.properties");
          break;
        case "81":
          if (Main.properties == null) {
            Main.properties = PropertiesUtil.load("config.properties");
          }
          if (MySQLDataSource.connection == null) {
            String DATABASE_HOSTNAME = Main.properties.getProperty("DATABASE_HOSTNAME");
            int DATABASE_PORT = Integer.parseInt(Main.properties.getProperty("DATABASE_PORT"));
            String DATABASE_SCHEMA = Main.properties.getProperty("DATABASE_SCHEMA");
            String DATABASE_USER = Main.properties.getProperty("DATABASE_USER");
            String DATABASE_PASSWORD = Main.properties.getProperty("DATABASE_PASSWORD");
            MySQLDataSource.connect(DATABASE_HOSTNAME, DATABASE_PORT, DATABASE_SCHEMA, DATABASE_USER, DATABASE_PASSWORD);
          }
          String SOCKET_HOSTNAME = Main.properties.getProperty("SOCKET_HOSTNAME");
          int SOCKET_PORT = Integer.parseInt(Main.properties.getProperty("SOCKET_PORT"));
          String body = "get: /api/v1/karyawan.txt";
          SocketUtil.listen(SOCKET_HOSTNAME, SOCKET_PORT, body);
          break;
        case "99":
          isExit = true;
          break;
        default:
          System.out.println("Invalid menu's number.");
      }

    }
  }

  public static void printMenu() {
    MainUtil.clearTerminal();
    System.out.println("MENU");
    System.out.println("1. Edit Status Karyawan (Probation, Kontrak, Tetap, Keluar)");
    System.out.println("2. Edit Data Karyawan (Nama Karyawan)");
    System.out.println("3. Absensi Karyawan");
    System.out.println("4. Izin Karyawan");
    System.out.println("5. Hitung Tunjangan Karyawan -> Tunjangan hanya berlaku untuk karyawan Tetap");
    System.out.println("6. Hitung Total Gaji Karyawan -> Hanya untuk Status != Keluar");
    System.out.println("7. Tampilkan Laporan per Status");
    System.out.println("99. Exit -> Finalize Close Method");
  }

  public static void showMenu() {
    boolean isExit = false;

    while (!isExit) {
      MainUtil.pressAnyKeyToContinue(bufferedReader);

      printMenu();

      String menuNumber = "";

      try {
        System.out.print("Enter the menu's number: ");

        menuNumber = bufferedReader.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }

      switch (menuNumber) {
        case "1":
          break;
        case "2":
          break;
        case "3":
          break;
        case "4":
          break;
        case "5":
          break;
        case "6":
          break;
        case "7":
          break;
        case "99":
          isExit = true;
          break;
        default:
          System.out.println("Invalid menu's number.");
      }

    }
  }
}
