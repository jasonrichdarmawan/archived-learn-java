import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class UpdateDataMahasiswa {
  public static ArrayList<MahasiswaBlueprint> showMenu(BufferedReader br, ArrayList<MahasiswaBlueprint> mahasiswas) {
    // nama => John, John S => id => focusMahasiswa.
    SearchMahasiswaUtils search = new SearchMahasiswaUtils(mahasiswas, br);

    if (search.getFocusMahasiswa() != null) {
      Main.clearConsole();
      MahasiswaUtils.printMahasiswaInfo(search.getFocusMahasiswa());
      String decision = "";
      try {
        System.out.print("Lanjut untuk Pemberian Nilai ke Mahasiswa? Yes or No: ");
        decision = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      boolean isExit = false; // for future development. not defined in SDR.
      while (isExit == false) {
        switch (decision.toLowerCase()) {
          case "y":
          case "yes":
            int index = search.getIndex();
            mahasiswas = InputDataMahasiswa.showMenu(br, mahasiswas, true, index);
            isExit = true;
            break;
          // for future development. not defined in SDR.
          case "n":
          case "no":
            isExit = true;
            System.out.println("Canceling..");
            break;
          default:
            isExit = true;
            System.out.println("Canceling..");
        }
      }
    } else {
      System.out.println("Mahasiswa tidak ditemukan.");
    }

    return mahasiswas;
  }
}
