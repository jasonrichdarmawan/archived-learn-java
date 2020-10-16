package com.company.program1;

import com.company.program1.util.SocketWorker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
  public static void main(String[] args) {
    Properties properties = new Properties();
    try {
      properties.load(new FileInputStream("config.properties"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    String SOCKET_HOSTNAME = properties.getProperty("SOCKET_HOSTNAME");
    int SOCKET_PORT = Integer.parseInt(properties.getProperty("SOCKET_PORT"));
    SocketWorker socketWorker = new SocketWorker(SOCKET_HOSTNAME, SOCKET_PORT, "mahasiswa2.json");
  }
}
