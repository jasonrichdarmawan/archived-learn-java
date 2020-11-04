package com.example.bankaccount;

import org.apache.catalina.User;

import java.util.regex.Pattern;

public class PatternTester {
  public static void Full_Name() {
    String[] pass = new String[]{"abcd abcd", "abcdefgh"};
    for (String test : pass) {
      System.out.println("Full_Name Pass: " + Pattern.matches("\\s*(?:[a-zA-Z]\\s*){8,35}$", test));
    }
    String[] fail = new String[]{"abcd     ", "12345678"};
    for (String test : fail) {
      System.out.println("Full_Name Fail: " + Pattern.matches("\\s*(?:[a-zA-Z]\\s*){8,35}$", test));
    }
  }

  public static void User_ID() {
    String[] pass = new String[]{"abcdefgh3103", "abcdabcdabcd"};
    for (String test : pass) {
      System.out.println("User_ID Pass: " + Pattern.matches("[a-zA-Z0-9]{12}", test));
    }
    String[] fail = new String[]{"abcd        ", "abcd"};
    for (String test : fail) {
      System.out.println("User_ID Fail: " + Pattern.matches("[a-zA-Z0-9]{12}", test));
    }
  }

  public static void main(String[] args) {
    Full_Name();
    User_ID();
  }
}
