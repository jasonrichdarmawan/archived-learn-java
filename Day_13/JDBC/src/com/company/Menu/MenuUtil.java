package com.company.Menu;

import com.company.MainUtil;
import com.company.datasource.MySQLDataSource;

import java.io.IOException;
import java.sql.SQLException;

import static com.company.MainUtil.bufferedReader;
import static com.company.datasource.MySQLDataSource.*;

public class MenuUtil {
  public static void printMenu() {
    MainUtil.clearTerminal();
    System.out.println("Menu");
    System.out.println("1. Show all Record");
    System.out.println("2. Insert Record");
    System.out.println("3. Update Record");
    System.out.println("4. Delete Record");
    System.out.println("99. Exit");
  }

  public static void showMenu() {
    boolean isExit = false;
    while (!isExit) {
      MainUtil.pressAnyKeyToContinue();

      printMenu();
      System.out.print("Enter the menu's number: ");
      String menuNumber = "";
      try {
        menuNumber = bufferedReader.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }

      // MySQLDataSource static method => bufferedReader.readLine() => execute the sql statement.
      switch (menuNumber) {
        case "1":
          MySQLDataSource.readTableEMP();
          break;
        case "2":
          MySQLDataSource.insertRow();
          break;
        case "3":
          MySQLDataSource.updateRow();
          break;
        case "4":
          MySQLDataSource.deleteRow();
          break;
        case "99":
          // Release the connection.
          try {
            connection.close();
          } catch (SQLException throwables) {
            throwables.printStackTrace();
          }
          isExit = true;
          break;
        default:
          System.out.println("Invalid menu's number.");
      }
    }
  }
}
