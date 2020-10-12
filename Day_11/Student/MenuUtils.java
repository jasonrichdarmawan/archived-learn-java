import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class MenuUtils {
  public static void printMainMenu() {
    Main.clearConsole();
    System.out.println("MENU");
    System.out.println("1. Penerimaan Mahasiswa Baru");
    System.out.println("2. Pemberian Nilai ke Mahasiswa");
    System.out.println("3. Hitung Nilai Rata2 Mahasiswa");
    System.out.println("4. Laporan Nilai Mahasiswa (Multithread)");
    System.out.println("99. Exit");
  }

  public static void showMainMenu(BufferedReader br, ArrayList<MahasiswaBlueprint> mahasiswas) {
    boolean isExit = false;
    while (isExit == false) {
      String menu = "";
      Main.pressEnterToContinue(br); // to prevent Main.clearConsole(); called right away.
      printMainMenu();
      try {
        System.out.print("Masukkan nomor menu: ");
        menu = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      switch (menu) {
        case "1":
          // static method => return ArrrayList<Mahasiswa>
          mahasiswas = InputDataMahasiswa.showMenu(br, mahasiswas);
          break;
        case "2":
          // similar to case "1"
          mahasiswas = UpdateDataMahasiswa.showMenu(br, mahasiswas);
          break;
        case "3":
          MahasiswaUtils.printMahasiswasMedianScore(mahasiswas);
          break;
        case "4":
          NormalThread.print(3, mahasiswas);
          NormalThread.write(3, mahasiswas);
          break;
        case "99":
          isExit = true;
          break;
        default:
          System.out.println("Invalid menu's number.");
      }
    }
  }
}