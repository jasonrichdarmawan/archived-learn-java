package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainUtil {
  public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

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
