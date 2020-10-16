package com.company.program1.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Socket;

public class SocketWorker implements Runnable {
  private Socket socket;
  private DataOutputStream dataOutputStream;
  private BufferedReader bufferedReader;

  public SocketWorker(String hostname, int port, String fileName) {
    try {
      this.socket = new Socket(hostname, port);
      this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
      this.bufferedReader = new BufferedReader(new FileReader(fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }

    Thread thread = new Thread(this);
    thread.start();
  }

  @Override
  public void run() {
    String line = "";
    while (true) {
      try {
        if ((line = bufferedReader.readLine()) == null) break;
      } catch (IOException e) {
        e.printStackTrace();
      }

      Object object = null;
      try {
        object = new JSONParser().parse(line);
      } catch (ParseException e) {
        e.printStackTrace();
      }

      JSONObject jsonObject = (JSONObject) object;
      try {
        System.out.println("Sending to socket hostname: " + socket.getLocalAddress() + " port: " + socket.getLocalPort() + " payload: " + jsonObject.toJSONString());
        dataOutputStream.writeUTF(jsonObject.toJSONString());
        dataOutputStream.flush();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

//    try {
//      socket.close();
//      dataOutputStream.close();
//      bufferedReader.close();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

    // to maintain active connection.
    while (true) {
    }
  }
}
