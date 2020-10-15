package com.company.server.ServerSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketUtil {
  public static ServerSocket serverSocket;

  public static void run(int SOCKET_PORT) {
    try {
      serverSocket = new ServerSocket(SOCKET_PORT);
      System.out.println("Server is listening request on SOCKET_PORT: " + serverSocket.getLocalPort());
    } catch (IOException e) {
      e.printStackTrace();
    }

    // accept multiple connections. current usage: to maintain the port listener.
    while (true) {
      try {
        Socket socket = serverSocket.accept();
        ServerSocketWorker serverSocketWorker = new ServerSocketWorker(socket);
        Thread thread = new Thread(serverSocketWorker);
        thread.start();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
