package com.company;

import com.company.controller.FTPClientController;
import com.company.model.FTPClientModel;
import com.company.view.FTPClientView;

import java.io.*;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) {
    FTPClientModel ftpClientModel = new FTPClientModel("localhost", 21, "kidfrom", "123Abcd");
    FTPClientView ftpClientView = new FTPClientView();
    FTPClientController ftpClientController = new FTPClientController(ftpClientModel, ftpClientView);
    ftpClientController.showMenu();
  }
}
