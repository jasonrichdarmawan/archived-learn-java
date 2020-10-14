package com.company.Menu;

import com.company.Employee.Manager;
import com.company.Employee.Staff;
import com.company.JSON.JSONReader;
import com.company.JSON.JSONWriter;
import com.company.MainUtil;

import java.io.IOException;

import static com.company.MainUtil.*;

public class MenuUtil {
  public static void printMenu() {
    MainUtil.clearTerminal();
    System.out.println("Menu");
    System.out.println("1. Buat Worker -> 2 ArrayList (Staff dan Manager)");
    System.out.println("2. Create JSON Format and Write to File (Staff.txt & Manager.txt)");
    System.out.println("3. Read JSON Format from a File, input Filename (Show on Screen)");
    System.out.println("4. Exit");
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

      switch (menuNumber) {
        case "1":
          String[] emails = {"steve@gmail.com", "steve.f@tempatkerja.com"};
          Staff staff = new Staff(1, "Steve", 1000000,11000000, 21, emails);
          staff.HitungTunjanganMakan();
          staffs.add(staff);

          emails = new String[]{"michael@gmail.com", "michael123@gmail.com"};
          staff = new Staff(2, "Michael", 1000000, 11000000, 22, emails);
          staff.HitungTunjanganMakan();
          staffs.add(staff);

          String[] telepons = {"0811 8751 555", "0889 0889 0669"};
          Manager manager = new Manager(3, "Jason R", 1000000,20000000, 22, telepons);
          manager.HitungTunjanganTransport();
          manager.HitungTunjanganEntertaint();
          managers.add(manager);

          telepons = new String[]{"1234 5678 9999"};
          manager = new Manager(4, "David", 1000000,20000000, 22, telepons);
          manager.HitungTunjanganTransport();
          manager.HitungTunjanganEntertaint();
          managers.add(manager);
          break;
        case "2":
          JSONWriter.writeManagers(managers);
          JSONWriter.writeStaffs(staffs);
          break;
        case "3":
          System.out.print("Input the file name: ");
          String fileName = "";
          try {
            fileName = bufferedReader.readLine();
          } catch (IOException e) {
            e.printStackTrace();
          }
          switch (fileName) {
            case "Staff.txt":
              JSONReader.readStaffs(fileName);
              break;
            case "Manager.txt":
              JSONReader.readManagers(fileName);
              break;
          }
          break;
        case "4":
          isExit = true;
          break;
        default:
          System.out.println("Unknown menu's number.");

      }


    }
  }
}
