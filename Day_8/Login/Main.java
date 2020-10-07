import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) {
    boolean authState = LoginUtils.Authenticate(br);

    // break if authState is false;
    if (!authState)
      return;

    // this static method is similar to how React Router DOM works.
    // params = switch & case.
    MenuUtils.mainMenu();
  }

  // reuseable static method.
  public static void clearConsole() {
    System.out.print("\033[H\033[2J");
  }

  // reuseable static method.
  public static void pressEnterToContinue() {
    System.out.print("Press enter to continue. ");
    try {
      br.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
