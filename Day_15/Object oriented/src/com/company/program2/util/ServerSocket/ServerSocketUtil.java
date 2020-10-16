package com.company.program2.util.ServerSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class ServerSocketUtil implements Runnable {
  private ServerSocket serverSocket;
  private final String DATABASE_API;
  private final String DATABASE;
  private final String DATABASE_HOSTNAME;
  private final int DATABASE_PORT;
  private final String DATABASE_SCHEMA;
  private final String DATABASE_USER;
  private final String DATABASE_PASSWORD;

  public ServerSocketUtil(Properties properties) {
    try {
      int SOCKET_PORT = Integer.parseInt(properties.getProperty("SOCKET_PORT"));
      this.serverSocket = new ServerSocket(SOCKET_PORT);
      System.out.println("Server is listening on port: " + this.serverSocket.getLocalPort());
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.DATABASE_API = properties.getProperty("DATABASE_API");
    this.DATABASE = properties.getProperty("DATABASE");
    this.DATABASE_HOSTNAME = properties.getProperty("DATABASE_HOSTNAME");
    this.DATABASE_PORT = Integer.parseInt(properties.getProperty("DATABASE_PORT"));
    this.DATABASE_SCHEMA = properties.getProperty("DATABASE_SCHEMA");
    this.DATABASE_USER = properties.getProperty("DATABASE_USER");
    this.DATABASE_PASSWORD = properties.getProperty("DATABASE_PASSWORD");
  }

  public void run() {
    // accept multiple connections. current usage: to maintain the port listener.
    while (true) {
      try {
        Socket socket = serverSocket.accept();
        ServerSocketWorker serverSocketWorker = new ServerSocketWorker(socket, DATABASE_API, DATABASE, DATABASE_HOSTNAME, DATABASE_PORT, DATABASE_SCHEMA, DATABASE_USER, DATABASE_PASSWORD);
        Thread thread = new Thread(serverSocketWorker);
        thread.start();
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
  }
}
