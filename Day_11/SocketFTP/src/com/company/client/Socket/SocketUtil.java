package com.company.client.Socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketUtil {
  public static Socket socket = null;
  public static void listen(String SOCKET_SERVER, int SOCKET_PORT, String body) {
    try {
      socket = new Socket(SOCKET_SERVER, SOCKET_PORT);

      System.out.println("Sending request: " + body);
      DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
      dout.writeUTF(body);
      dout.flush();

      SocketListenerWorker socketListener = new SocketListenerWorker();

    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
