import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MenuUtils {
  // reuseable static method.
  public static void printMenu() {
    // clean the terminal.
    Main.clearConsole();

    System.out.println("Menu:");
    System.out.println("1. Create & Input Data Mahasiswa.");
    System.out.println("2. Edit or Delete Data Mahasiswa");
    System.out.println("3. Tampilkan laporan Nilai Data Data Mahasiswa");
    System.out.println("4. Keluar dari aplikasi ini.");
    System.out.print("Enter the menu's number: ");
  }

  // structured as a Utils.
  // each switch call a method.
  public static void mainMenu() {
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    String menu = "";

    ArrayList<Mahasiswa> mahasiswas = new ArrayList<>();

    while (menu != "4") {
      printMenu();
      try {
        menu = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      switch (menu) {
        case "1":
          // this method will return back the modified ArrayList
          mahasiswas = MahasiswaUtils.newMahasiswa(mahasiswas);
          break;
        case "2":
          // similar concept to case 1.
          mahasiswas = MahasiswaUtils.editMahasiswa(mahasiswas);
          break;
        case "3":
          // this is just a sysout with bubble sort algorithm.
          MahasiswaUtils.printLaporanNilaiDataMahasiswa(mahasiswas);
          break;
        case "4":
          menu = "4";
          break;
        default:
          System.out.println("Invalid menu's number. Please enter the correct menu's number.");
      }
    }
  }
}
