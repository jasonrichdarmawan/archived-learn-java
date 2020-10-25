package com.example.designpattern.adapter;

import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketToFTPAdapter implements Runnable {
  public void serverSocket() {
    try {
      ServerSocket serverSocket = new ServerSocket(8082);
      Socket socket = serverSocket.accept();
      DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

      InputStream inputStream = new ByteArrayInputStream(dataInputStream.readAllBytes());

      dataInputStream.close();
      serverSocket.close();

      FTPClient ftpClient = new FTPClient();
      ftpClient.connect("localhost", 21);
      ftpClient.login("kidfrom", "123Abcd");
      ftpClient.storeFile("LaporanKaryawan.txt", inputStream);

      inputStream.close();
      ftpClient.disconnect();
    } catch (IOException e) {
      e.printStackTrace();
    }


  }

  @Override
  public void run() {
    serverSocket();
  }
}
