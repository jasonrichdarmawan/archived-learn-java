import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class InputDataMahasiswa {
  // show menu => return array list of mahasiswa.
  public static ArrayList<MahasiswaBlueprint> showMenu(BufferedReader br, ArrayList<MahasiswaBlueprint> mahasiswas, boolean set, int index) {
    int id = mahasiswas.size() + 1;
    String nama = "";
    double bahasaInggris = 0.0d;
    double fisika = 0.0d;
    double algoritma = 0.0d;

    boolean isExit = false; // for future development. not defined in SDR.
    while (isExit == false) {
      try {
        Main.pressEnterToContinue(br);
        Main.clearConsole();

        System.out.print("Nama mahasiswa: ");
        nama = br.readLine();

        System.out.print("Nilai Bahasa Inggris: ");
        bahasaInggris = Double.parseDouble(br.readLine());

        System.out.print("Nilai Fisika: ");
        fisika = Double.parseDouble(br.readLine());

        System.out.print("Nilai Algoritma: ");
        algoritma = Double.parseDouble(br.readLine());

        Main.clearConsole();
        ArrayList<Double> nilai = new ArrayList<>();
        nilai.add(bahasaInggris);
        nilai.add(fisika);
        nilai.add(algoritma);
        MahasiswaBlueprint mahasiswa = null;
        if (set == true) {
          // menu 2
          int focusID = mahasiswas.get(index).getID(); 
          mahasiswa = new MahasiswaBlueprint(focusID, nama, nilai);
        } else {
          // menu 1
          mahasiswa = new MahasiswaBlueprint(id, nama, nilai);
        }
        MahasiswaUtils.printMahasiswaInfo(mahasiswa);
        System.out.print("Is this information correct? Yes or No: ");
        String decision = br.readLine();
        switch (decision.toLowerCase()) {
          case "y":
          case "yes":
            if (set == true) {
              mahasiswas.set(index, mahasiswa);
            } else {
              mahasiswas.add(mahasiswa);
            }
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
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return mahasiswas;
  }

  public static ArrayList<MahasiswaBlueprint> showMenu(BufferedReader br, ArrayList<MahasiswaBlueprint> mahasiswas) {
    return showMenu(br, mahasiswas, false, 0);
  }
}
