package com.company.client;

import com.company.client.Menu.MenuUtil;
import com.company.common.PropertiesUtil;

import java.util.Properties;

public class Main {
  public static String FTP_SERVER;
  public static int FTP_PORT;
  public static String FTP_USER;
  public static String FTP_PASSWORD;

  public static String SOCKET_SERVER;
  public static int SOCKET_PORT;

  public static void main(String[] args) {
    Properties properties = PropertiesUtil.load(args[0]);

    FTP_SERVER = properties.getProperty("FTP_SERVER");
    FTP_PORT = Integer.parseInt(properties.getProperty("FTP_PORT"));
    FTP_USER = properties.getProperty("FTP_USER");
    FTP_PASSWORD = properties.getProperty("FTP_PASSWORD");

    SOCKET_SERVER = properties.getProperty("SOCKET_SERVER");
    SOCKET_PORT = Integer.parseInt(properties.getProperty("SOCKET_PORT"));

    MenuUtil.showMenu();
  }
}
