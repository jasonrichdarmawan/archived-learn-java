package com.company.server;

import com.company.common.PropertiesUtil;
import com.company.server.ServerSocket.ServerSocketWorker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Main {
  public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
  public static int SOCKET_PORT;
  public static ServerSocket serverSocket;

  public static void main(String[] args) {
    Properties properties = PropertiesUtil.load(args[0]);

    SOCKET_PORT = Integer.parseInt(properties.getProperty("SOCKET_PORT"));
    try {
      serverSocket = new ServerSocket(SOCKET_PORT);
      System.out.println("Server is listening request on SOCKET_PORT: " + SOCKET_PORT);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // accept multiple connections. current usage: to maintain the port listener.
    while (true) {
      try {
        Socket socket = serverSocket.accept();
        ServerSocketWorker serverSocketWorker = new ServerSocketWorker(socket);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
