package com.company.client.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.company.client.common.FTPClientUtil.ftpClient;

public class FTPUploadWorker implements Runnable {
  private String FTP_SERVER;
  private int FTP_PORT;
  private String FTP_USER;
  private String FTP_PASSWORD;
  private String localFilePath;

  public FTPUploadWorker(String FTP_SERVER, int FTP_PORT, String FTP_USER, String FTP_PASSWORD, String localFilePath) {
    this.FTP_SERVER = FTP_SERVER;
    this.FTP_PORT = FTP_PORT;
    this.FTP_USER = FTP_USER;
    this.FTP_PASSWORD = FTP_PASSWORD;
    this.localFilePath = localFilePath;

    Thread thread = new Thread(this);
    thread.start();
    try {
      thread.join(); // waits for this thread to die => guarantee the worker is executed in order.
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    FTPClientUtil.forceConnect(FTP_SERVER, FTP_PORT, FTP_USER, FTP_PASSWORD, true);

    try {
      File file = new File(localFilePath);

      String remoteFilePath = localFilePath;
      InputStream inputStream = new FileInputStream(file);

      System.out.println("Start uploading the file");
      boolean isDone = ftpClient.storeFile(remoteFilePath, inputStream);
      inputStream.close();
      if (isDone) {
        System.out.println("The file is uploaded successfully.");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
