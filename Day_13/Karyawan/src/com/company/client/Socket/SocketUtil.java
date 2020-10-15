package com.company.client.Socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketUtil {
  public static Socket socket = null;
  public static void listen(String SOCKET_HOSTNAME, int SOCKET_PORT, String body) {
    try {
      socket = new Socket(SOCKET_HOSTNAME, SOCKET_PORT);

      System.out.println("Sending request: " + body);
      DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
      dout.writeUTF(body);
      dout.flush();

      SocketListenerWorker socketListenerWorker = new SocketListenerWorker();
      Thread thread = new Thread(socketListenerWorker);
      thread.start();

    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
