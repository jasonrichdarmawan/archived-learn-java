import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MahasiswaUtils {
  // reuseable static method.
  public static void printMahasiswa(Mahasiswa mahasiswa) {
    System.out.println("ID: " + mahasiswa.getiID());
    System.out.println("Name: " + mahasiswa.getsName());
    System.out.println("Bahasa Inggris: " + mahasiswa.getarrDNilai().get(0));
    System.out.println("Fisika: " + mahasiswa.getarrDNilai().get(1));
    System.out.println("Algoritma: " + mahasiswa.getarrDNilai().get(2));
  }

  // this method modify argument ArrayList and return it back.
  public static ArrayList<Mahasiswa> newMahasiswa(ArrayList<Mahasiswa> mahasiswas) {
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    String sName = "";
    double dBahasaInggris = 0.0F;
    double dFisika = 0.0F;
    double dAlgoritma = 0.0F;
    try {
      System.out.print("Enter the student's name: ");
      sName = br.readLine();

      System.out.print("Enter the student's score for Bahasa Inggris: ");
      dBahasaInggris = Double.parseDouble(br.readLine());
  
      System.out.print("Enter the student's score for Fisika: ");
      dFisika = Double.parseDouble(br.readLine());
  
      System.out.print("Enter the student's score for Algoritma: ");
      dAlgoritma = Double.parseDouble(br.readLine());
    } catch (IOException e) {
      e.printStackTrace();
    }

    // insert the scores to an ArrayList.
    ArrayList<Double> arrDNilai = new ArrayList<>();
    arrDNilai.add(dBahasaInggris);
    arrDNilai.add(dFisika);
    arrDNilai.add(dAlgoritma);

    // create new object and insert it to the ArrayList.
    Mahasiswa mahasiswa = new Mahasiswa(mahasiswas.size() + 1, sName, arrDNilai);
    mahasiswas.add(mahasiswa);

    Main.pressEnterToContinue();

    return mahasiswas;
  }

  // this method modify argument ArrayList and return it back.
  public static ArrayList<Mahasiswa> editMahasiswa(ArrayList<Mahasiswa> mahasiswas) {
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    int iID = 0;
    try {
      System.out.print("Enter the student's ID: ");
      iID = Integer.parseInt(br.readLine());
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (int index = 0; index < mahasiswas.size(); index++) {
      Mahasiswa mahasiswa = mahasiswas.get(index);

      if (mahasiswa.getiID() == iID) {
        Main.clearConsole();
        System.out.println("Mahasiswa found.");
        printMahasiswa(mahasiswa);

        ArrayList<Double> arrDNilai = new ArrayList<>();

        String sName = "";
        double dBahasaInggris = 0.0d;
        double dFisika = 0.0d;
        double dAlgoritma = 0.0d;
        try {
          System.out.print("Enter the new value for Name: ");
          sName = br.readLine();
  
          System.out.print("Enter the new value for Bahasa Inggris: ");
          dBahasaInggris = Double.parseDouble(br.readLine());
          
          System.out.print("Enter the new value for Fisika: ");
          dFisika = Double.parseDouble(br.readLine());
          
          System.out.print("Enter the new value for Algoritma: ");
          dAlgoritma = Double.parseDouble(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          arrDNilai.add(dBahasaInggris);
          arrDNilai.add(dFisika);
          arrDNilai.add(dAlgoritma);
        }

        // temporary object, to enable feature "Cancel edit"
        Mahasiswa temporary = new Mahasiswa(mahasiswa.getiID(), sName, arrDNilai);
        Main.clearConsole();
        printMahasiswa(temporary);

        System.out.print("Made the changes? Yes / No: ");
        String decision = "";
        try {
          decision = br.readLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
        switch (decision.toLowerCase()) {
          case "y":
          case "yes":
            mahasiswas.set(index, temporary);
            break;
          case "n":
          case "no":
            break;
          default:
            System.out.println("Canceling..");
        }

        Main.pressEnterToContinue();
      } else {
        System.out.println("Mahasiswa with the specified ID is not found.");
        Main.pressEnterToContinue();
      }
    }

    if (mahasiswas.size() == 0) {
      System.out.println("ArrayList<Mahasiswa> is empty.");
      Main.pressEnterToContinue();
    }

    return mahasiswas;
  }

  // this just sort the argument ArrayList and prints it.
  public static void printLaporanNilaiDataMahasiswa(ArrayList<Mahasiswa> mahasiswas) {
    // this format is used by sysout format.
    // %-2d: maximum length 2, decimal / integer.
    String format = "%-2d  |  %-10s  |  %-11.2f  |  %-6.2f  |  %-9.2f  %n";

    Main.clearConsole();
    System.out.println("Laporan Nilai Data Mahasiswa: ");
    System.out.println("ID  |     Nama     |  Bhs Inggris  |  Fisika  |  Algoritma");

    // bubble sort algorithm
    for (int i = 0; i < mahasiswas.size() - 1; i++) {
      for (int j = 0; j < mahasiswas.size() - 1; j++) {
        if (mahasiswas.get(j).getiID() > mahasiswas.get(j + 1).getiID()) {
          Mahasiswa temporary = mahasiswas.get(j);
          mahasiswas.set(j, mahasiswas.get(j + 1));
          mahasiswas.set(j + 1, temporary);
        }
      }
    }

    for (Mahasiswa mahasiswa : mahasiswas) {
      ArrayList<Double> arrDNilai = mahasiswa.getarrDNilai();
      System.out.format(format, mahasiswa.getiID(), mahasiswa.getsName(), arrDNilai.get(0), arrDNilai.get(1),
          arrDNilai.get(2));
    }

    Main.pressEnterToContinue();
  }
}
