package com.company.server.ServerSocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.net.Socket;

public class ServerSocketWorker implements Runnable {
  private Socket socket;

  public ServerSocketWorker(Socket socket) {
    this.socket = socket;
    Thread thread = new Thread(this);
    thread.start();
  }

  @Override
  public void run() {
    try {
      // current usage: to maintain listener.
      boolean isExit = false;
      while (!isExit) {
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        String request = "";
        try {
          request = dis.readUTF();
        } catch (EOFException e) {
          e.printStackTrace();
          socket.close();
        }

        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
        switch (request) {
          case "get: /api/v1/file.txt":
            System.out.println("Receiving request 'get: /api/v1/file.txt'");
            try {
              FileReader fr = new FileReader("./file.txt");
              BufferedReader br = new BufferedReader(fr);
              String response = "";
              while ((response = br.readLine()) != null) {
                dout.writeUTF(response);
                dout.flush();
                System.out.println("Sending response: " + response);
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
