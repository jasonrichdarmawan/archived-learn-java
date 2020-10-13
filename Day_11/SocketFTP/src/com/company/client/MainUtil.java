package com.company.client;

import java.io.BufferedReader;
import java.io.IOException;

public class MainUtil {
  public static void clearTerminal() {
    System.out.print("\033[H\033[2J");
  }

  public static void pressAnyKeyToContinue(BufferedReader br) {
    System.out.print("Press any key to continue. ");
    try {
      br.readLine();
      System.out.println("");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
