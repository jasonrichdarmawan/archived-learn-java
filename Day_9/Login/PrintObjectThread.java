import java.util.ArrayList;

public class PrintObjectThread extends Thread {
  static final String file = "./database.txt";

  private ArrayList<Mahasiswa> mahasiswas = null;

  PrintObjectThread(ArrayList<Mahasiswa> mahasiswas) {
    this.mahasiswas = mahasiswas;
  }

  public void run() {
    print(this.mahasiswas);
  }

  // this is similar to WriteDatabaseThread.java
  // the difference is:
  // 1. it pass the Object to static method from MahasiswaUtils class.
  public static void print(ArrayList<Mahasiswa> mahasiswas) {
    Main.clearConsole();
    for (Mahasiswa mahasiswa : mahasiswas) {
      System.out.println("");
      MahasiswaUtils.printMahasiswa(mahasiswa);
    }
  }
}
