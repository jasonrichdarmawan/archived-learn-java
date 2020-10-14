package com.company;

import com.company.Employee.Manager;
import com.company.Employee.Staff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainUtil {
  public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

  public static int thisMonthTotalWorkDays = 22;
  public static float maxTunjanganMakan = 220000;
  public static float maxTunjanganTransport = 440000;
  public static float maxTunjanganEntertaint = 440000;

  public static ArrayList<Staff> staffs = new ArrayList<>();
  public static ArrayList<Manager> managers = new ArrayList<>();

  public static void clearTerminal() {
    System.out.print("\033[H\033[2J");
  }

  public static void pressAnyKeyToContinue() {
    System.out.print("Press any key to continue. ");
    try {
      bufferedReader.readLine();
      System.out.println("");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
