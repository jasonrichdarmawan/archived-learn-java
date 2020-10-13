package com.company.client.common;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class FTPClientUtil {
  public static FTPClient ftpClient = new FTPClient();

  public static void forceConnect(String FTP_SERVER, int FTP_PORT, String FTP_USER, String FTP_PASSWORD, boolean enterLocalPassiveMode) {
    while (!ftpClient.isConnected()) {
      try {
        ftpClient.connect(FTP_SERVER, FTP_PORT);
        ftpClient.login(FTP_USER, FTP_PASSWORD);
        if (enterLocalPassiveMode) {
          ftpClient.enterLocalPassiveMode();
        }
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static void disconnect() {
    try {
      if (ftpClient.isConnected()) {
        ftpClient.logout();
        ftpClient.disconnect();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      System.out.println("org.apache.commons.net.ftp.FTPClient isConnect(): " + ftpClient.isConnected());
    }
  }
}
