package com.company.program2;

import com.company.program2.util.LoginUtil;
import com.company.program2.util.MenuUtil;

public class Main {

  public static void main(String[] args) {
   LoginUtil loginUtil = new LoginUtil();

   if (!loginUtil.isAuthState()) {
     return;
   }

    MenuUtil menuUtil = new MenuUtil();
    menuUtil.showMenu();
  }
}
