import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;

import java.util.regex.Pattern;

public class LoginUtils {
  static final String username = "./username.txt";
  static final String password = "./password.txt";

  static FileReader frUsername;
  static FileReader frPassword;

  // intentional. otherwise it will throw FileNotFoundException.
  // if used in one go. username = new BufferedReader(new FileReader(username));
  static {
    try {
      frUsername = new FileReader(username);
      frPassword = new FileReader(password);
    } catch (IOException e) {
      System.err.print(e);
    }
  }

  public static boolean Authenticate(BufferedReader br) {
    // similar reason to try and catch used in Main.java
    String username = "";
    String password = "";
    try {
      username = new BufferedReader(frUsername).readLine();
      password = new BufferedReader(frPassword).readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }

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

    // TODO: uncomment.
    // break if pattern is not match.
    System.out.print("Enter Username: ");

    // similar reason to try and catch used in Main.java
    String inputUsername = "";
    try {
      inputUsername = br.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (!Pattern.matches(sUsernamePattern, inputUsername))
      return false;

    // break if pattern is not match
    System.out.print("Enter Password: ");

    // similar reason to try and catch used in Main.java
    String inputPassword = "";
    try {
      inputPassword = br.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (!Pattern.matches(sPasswordPattern, inputPassword))
      return false;

    // break if username / password did not match
    if (!username.equals(inputUsername) && !password.equals(inputPassword))
      return false;

    // return true if everything is passed.
    return true;
  }
}
