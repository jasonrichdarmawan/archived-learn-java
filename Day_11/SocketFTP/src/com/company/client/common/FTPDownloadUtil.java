package com.company.client.common;

import com.company.client.MainUtil;
import org.apache.commons.net.ftp.FTPFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Pattern;

import static com.company.client.Menu.MenuUtil.bufferedReader;
import static com.company.client.common.FTPClientUtil.ftpClient;

public class FTPDownloadUtil {
  public static void listFiles(String filePath) {
    MainUtil.clearTerminal();
    System.out.println(filePath == "" ? "." : filePath);

    try {
      // lists files and directories in the current working directory
      FTPFile[] files = ftpClient.listFiles("/" + filePath);

      for (int i = 0; i < files.length; i++) {
        FTPFile file = files[i];
        String line = "";
        if (i < (files.length - 1)) {
          line = "├──" + file.getName();
        } else {
          line = "└──" + file.getName();
        }
        System.out.println(line);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void showMenu(String FTP_SERVER, int FTP_PORT, String FTP_USER, String FTP_PASSWORD) {
    FTPClientUtil.forceConnect(FTP_SERVER, FTP_PORT, FTP_USER, FTP_PASSWORD, true);

    listFiles("");

    boolean isExit = false;
    while (!isExit) {
      String remoteFilePath = "";
      try {
        // (\\w+) any word at least once
        // literal dot cone
        // (\\w+) any word at least once
        String filePattern = ".*[.](\\w+)";
        System.out.print("Enter the file path or /q to exit: ");
        remoteFilePath = bufferedReader.readLine();
        if (Pattern.matches(filePattern, remoteFilePath)) {
          String[] arrstr = remoteFilePath.split("/");
          OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(arrstr[arrstr.length - 1]));
          boolean success = ftpClient.retrieveFile(remoteFilePath, outputStream);
          outputStream.close();
          if (success) {
            System.out.println("The file has been downloaded successfully.");
          }
          // download
        } else {
          switch (remoteFilePath) {
            case "/q":
              isExit = true;
              break;
            default:
              listFiles("test");
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
