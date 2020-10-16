package com.company.program2.util;

import java.io.*;
import java.util.regex.Pattern;

public class LoginUtil {
  private boolean authState;

  public LoginUtil() {
    authenticate();
  }

  public void authenticate() {
    String username = "";
    String inputUsername = "";
    String password = "";
    String inputPassword = "";

    // (\\w+) any word character at least once
    // @ // @ at least once.
    // (\\w+) // any word character at least once
    // (\\.{1}) // literal dot once
    // (\\w+) // any word character at least once
    String sUsernamePattern = "(\\w+)@(\\w+)(\\.{1})(\\w+)";

    // (?=.*[a-z]) // lower case letter at least once
    // (?=.*[A-Z]) // upper case letter at least once.
    // (?=.*[@#$%^&+=]) // special character at least once
    // (?=\\S+$) // no whitespace.
    // .{9,} // any at least 9.
    String sPasswordPattern = "(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{9,}";

    try {
      username = new BufferedReader(new FileReader("username.txt")).readLine();
      password = new BufferedReader(new FileReader("password.txt")).readLine();

      System.out.print("Enter the username: ");
      inputUsername = new BufferedReader(new InputStreamReader(System.in)).readLine();

      if (!Pattern.matches(sUsernamePattern, inputUsername)) {
        this.authState = false;
        return;
      }

      System.out.print("Enter the password: ");
      inputPassword = new BufferedReader(new InputStreamReader(System.in)).readLine();

      if (!Pattern.matches(sPasswordPattern, inputPassword)) {
        this.authState = false;
        return;
      }

      if (!username.equals(inputUsername) || !password.equals(inputPassword)) {
        this.authState = false;
        return;
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // if Regex check is passed => Username and password matched.
    this.authState = true;
  }

  public boolean isAuthState() {
    return authState;
  }
}