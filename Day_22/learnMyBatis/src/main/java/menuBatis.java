package main.java;

import main.java.mybatis.model.Student;
import main.java.mybatis.repository.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class menuBatis {

  private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

  public void clearTerminal() {
    System.out.print("\033[H\033[2J");
  }

  public void pressAnyKeyToContinue() {
    try {
      System.out.print("Press enter to continue. ");
      bufferedReader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void printMenu() {
    clearTerminal();
    System.out.println("MENU:");
    System.out.println("1. Insert");
    System.out.println("2. Update");
    System.out.println("3. Read By Id");
    System.out.println("4. Read All");
    System.out.println("5. Delete");
  }

  public void showMenu() {
    boolean isExit = false;
    while (!isExit) {
      pressAnyKeyToContinue();

      printMenu();
      System.out.print("Enter the menu number: ");
      String menuNumber = null;
      try {
        menuNumber = bufferedReader.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }

      switch (menuNumber) {
        case "1":
          try {
            System.out.print("Enter the name: ");
            String name = bufferedReader.readLine();
            System.out.print("Enter the branch: ");
            String branch = bufferedReader.readLine();
            System.out.print("Enter the percentage: ");
            Integer percentage = Integer.parseInt(bufferedReader.readLine());
            System.out.print("Enter the phone: ");
            Integer phone = Integer.parseInt(bufferedReader.readLine());
            System.out.print("Enter the email: ");
            String email = bufferedReader.readLine();
            mybatisInsert.insert(new Student(name, branch, percentage, phone, email));
          } catch (IOException e) {
            e.printStackTrace();
          }
          break;
        case "2":
          try {
            System.out.print("Enter the ID: ");
            Integer id = Integer.parseInt(bufferedReader.readLine());
            System.out.print("Enter the name: ");
            String name = bufferedReader.readLine();
            System.out.print("Enter the branch: ");
            String branch = bufferedReader.readLine();
            System.out.print("Enter the percentage: ");
            Integer percentage = Integer.parseInt(bufferedReader.readLine());
            System.out.print("Enter the phone: ");
            Integer phone = Integer.parseInt(bufferedReader.readLine());
            System.out.print("Enter the email: ");
            String email = bufferedReader.readLine();
            mybatisUpdate_byID.updateById(new Student(id, name, branch, percentage, phone, email));
          } catch (IOException e) {
            e.printStackTrace();
          }
          break;
        case "3":
          System.out.print("Enter the ID: ");
          try {
            mybatisRead_byID.Read_byID(Integer.parseInt(bufferedReader.readLine()));
          } catch (IOException e) {
            e.printStackTrace();
          }
          break;
        case "4":
          mybatisRead_All.readAll();
          break;
        case "5":
          System.out.print("Enter the ID: ");
          try {
            mybatisDelete_byID.deleteById(Integer.parseInt(bufferedReader.readLine()));
          } catch (IOException e) {
            e.printStackTrace();
          }
          break;
        case "6":
          isExit = true;
          break;
        default:
          System.out.println("Invalid menu number.");
      }
    }
  }
}
