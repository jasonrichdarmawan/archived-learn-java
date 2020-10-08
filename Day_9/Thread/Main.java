import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void printMenu() {
    clearConsole();
    System.out.println("Menu:");
    System.out.println("1. Simple Thread -> 10 Print ke Layar.");
    System.out.println("2. ThreadPool -> 10 Print ke Layar.");
    System.out.println("3. Simpan ke file secara Ascending dan Descending.");
    System.out.println("4. Exit");
  }

  public static void clearConsole() {
    System.out.print("\033[H\033[2J");
  }

  public static void pressEnterToContinue(BufferedReader br) {
    System.out.print("Press enter to continue. ");
    try {
      br.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    FileReader fr = null;
    try {
      fr = new FileReader("./" + args[0]);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    String menu = "";
    boolean isExit = false;
    while (!isExit) {

      try {
        printMenu();
        System.out.print("Enter the menu's number: ");
        menu = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();

      }
      int workersSize = Integer.parseInt(args[1]);
      String[] messages = TXTReader.ReturnStrings(args[0]);

      switch (menu) {
        case "1":
          SimpleThread.print(workersSize, messages);
          pressEnterToContinue(br);
          break;
        case "2":
          ThreadPool.print(workersSize, messages);
          pressEnterToContinue(br);
          break;
        case "3":
          // SimpleThread.print(workersSize, messages);

          // sedikit berbeda dengan SDR.
          // logic: messages sudah diolah terlebih dahulu.

          // SimpleThread is more superior compared to ThreadPool using
          // executor. Executor sometimes violate the fixed workersSize.
          // SimpleThread.asc(workersSize, messages);
          SimpleThread.dsc(workersSize, messages);

          pressEnterToContinue(br);
          break;
        case "4":
          isExit = true;
          break;
        default:
          System.out.println("Invalid menu.");
      }
    }
  }
}
