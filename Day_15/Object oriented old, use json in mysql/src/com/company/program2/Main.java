package com.company.program2;

import com.company.program2.util.MenuUtil;

public class Main {

  public static void main(String[] args) {
//    LoginUtil loginUtil = new loginUtil();
//
//    if (!loginUtil.isAuthState()) {
//      return;
//    }

    MenuUtil menuUtil = new MenuUtil();
    menuUtil.showMenu();
  }
}
