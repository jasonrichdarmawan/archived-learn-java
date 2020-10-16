package com.company.program2.util.FTP;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.util.Properties;
import java.util.regex.Pattern;

public class FTPClientUtil {
  private final FTPClient ftpClient = new FTPClient();
  private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

  public FTPClientUtil(Properties properties) {
    try {
      this.ftpClient.connect(properties.getProperty("FTP_HOSTNAME"), Integer.parseInt(properties.getProperty("FTP_PORT")));
      this.ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
      this.ftpClient.login(properties.getProperty("FTP_USER"), properties.getProperty("FTP_PASSWORD"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.ftpClient.enterLocalPassiveMode();
  }

  public void clearTerminal() {
    System.out.print("\033[H\033[2J");
  }

  public void pressAnyKeyToContinue() {
    try {
      System.out.print("Press enter to continue. ");
      bufferedReader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void listFiles(String filePath) {
    try {
      // lists files and directories in the current working directory
      FTPFile[] files = ftpClient.listFiles("/" + filePath);

      clearTerminal();
      System.out.println(filePath == "" ? "." : filePath);

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

  public void downloadMenu() {
    listFiles("");

    boolean isExit = false;
    while (!isExit) {
      String remoteFilePath = "";

      // (\\w+) any word at least once
      // literal dot cone
      // (\\w+) any word at least once
      String filePattern = ".*[.](\\w+)";

      try {
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

        } else {

          switch (remoteFilePath) {
            case "/q":
              isExit = true;
              break;
            default:
              clearTerminal();
              listFiles(remoteFilePath);
          }

        }

      } catch (IOException e) {
        e.printStackTrace();
      }

    }

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

  public void close() {
    try {
      this.ftpClient.logout();
      this.ftpClient.disconnect();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
