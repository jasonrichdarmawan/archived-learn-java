package com.company.model;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FTPClientModel {
  private final FTPClient ftpClient = new FTPClient();

  public FTPClientModel(String hostname, int port, String user, String password) {
    try {
      this.ftpClient.connect(hostname, port);
      this.ftpClient.login(user, password);
      this.ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.ftpClient.enterLocalPassiveMode();
  }

  public FTPClient getFtpClient() {
    return ftpClient;
  }

  public void uploadFile(String remoteFilePath, InputStream inputStream) {
    boolean isDone = false;
    System.out.println("Start uploading the file.");
    try {
      isDone = this.ftpClient.storeFile(remoteFilePath, inputStream);
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (isDone) {
      System.out.println("The file is uploaded successfully.");
    }
  }
}
