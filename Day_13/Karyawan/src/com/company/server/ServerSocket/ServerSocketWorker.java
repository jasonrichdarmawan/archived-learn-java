package com.company.server.ServerSocket;

import com.company.server.util.JSONReaderUtil;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

public class ServerSocketWorker implements Runnable {
  private Socket socket;

  public ServerSocketWorker(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    try {
      // current usage: to maintain listener.
      boolean isExit = false;
      while (!isExit) {
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        String request = "";
        try {
          request = dataInputStream.readUTF();
        } catch (EOFException e) {
          e.printStackTrace();
          socket.close();
        }

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        switch (request) {
          case "get: /api/v1/karyawan.txt":
            System.out.println("Receiving request 'get: /api/v1/karyawan.txt'");
            try {
              List<JSONObject> list = JSONReaderUtil.readKaryawan("karyawan.txt");
              Iterator iterator = list.iterator();
              while (iterator.hasNext()) {
                JSONObject jsonObject = (JSONObject) iterator.next();
                dataOutputStream.writeUTF(jsonObject.toJSONString());
                dataOutputStream.flush();
                System.out.println("Sending response: " + jsonObject.toJSONString());
              }
            } catch (FileNotFoundException e) {
              e.printStackTrace();
            }
            break;
          default:
            socket.close();
            isExit = true;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
