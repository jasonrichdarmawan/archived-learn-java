package com.company.controller;

import com.company.model.FTPClientModel;
import com.company.view.FTPClientView;

import java.io.*;
import java.util.regex.Pattern;

public class FTPClientController {
  private final FTPClientModel ftpClientModel;
  private final FTPClientView ftpClientView;
  private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

  public FTPClientController(FTPClientModel ftpClientModel, FTPClientView ftpClientView) {
    this.ftpClientModel = ftpClientModel;
    this.ftpClientView = ftpClientView;
  }

  public void clearTerminal() {
    System.out.print("\033[H\033[2J");
  }

  public void listFiles(String filePath) {
    try {
      this.ftpClientView.printListFiles(this.ftpClientModel.getFtpClient().listFiles("/" + filePath));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void showDownloadMenu() {
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
          boolean success = ftpClientModel.getFtpClient().retrieveFile(remoteFilePath, outputStream);
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
    this.ftpClientModel.uploadFile(remoteFilePath, inputStream);
  }

  public void showUploadMenu() {
    boolean isExit = false;
    while (!isExit) {
      System.out.print("Enter the file path: ");
      String remoteFilePath = null;
      try {
        remoteFilePath = bufferedReader.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }

      InputStream inputStream = null;
      try {
        inputStream = new FileInputStream(remoteFilePath);
        isExit = true;
        uploadFile(remoteFilePath, inputStream);
      } catch (FileNotFoundException e) {
        System.out.println("File not found.");
        isExit = false;
      }
    }
  }

  public void printMenu() {
    clearTerminal();
    System.out.println("Menu: ");
    System.out.println("1. Download: List Files -> Download.");
    System.out.println("2. Upload: File Path -> Upload");
    System.out.println("3. Exit");
    System.out.print("Enter the menu's number: ");
  }

  public void showMenu() {
    boolean isExit = false;
    while (!isExit) {
      printMenu();

      String menuNumber = null;
      try {
        menuNumber = bufferedReader.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      switch (menuNumber) {
        case "1":
          showDownloadMenu();
          break;
        case "2":
          showUploadMenu();
          break;
        case "3":
          break;
        default:
          System.out.println("Invalid menu number");
      }
    }
  }
}
