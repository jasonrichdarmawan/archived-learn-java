package com.company.client;

import com.company.client.util.MenuUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

public class Main {
  public static Properties properties = null;

  public static int thisMonthTotalWorkDays = 22;
  public static int currentWorkDays = 21;
  public static float maxTunjanganMakan = 220000;
  public static float maxTunjanganTransport = 440000;
  public static float maxCutiPerBulan = 1;

  public static void main(String[] args) {
    MenuUtil.showPreMenu();
  }
}
