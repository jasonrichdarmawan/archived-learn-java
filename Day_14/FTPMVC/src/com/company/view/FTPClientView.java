package com.company.view;

import org.apache.commons.net.ftp.FTPFile;

public class FTPClientView {
  public void clearTerminal() {
    System.out.print("\033[H\033[2J");
  }

  public void printListFiles(FTPFile[] files) {
    clearTerminal();

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
  }
}
