import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

  static final String file = "./database.txt";

  public static ArrayList<Mahasiswa> readDatabase() {
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader(file));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    String sLine = "";

    ArrayList<Mahasiswa> mahasiswas = new ArrayList<>();

    try {
      while ((sLine = br.readLine()) != null) {
        String[] sLineStrings = sLine.split(",");
        ArrayList<Double> arrDNilai = new ArrayList<>();
  
        arrDNilai.add(Double.parseDouble(sLineStrings[2]));
        arrDNilai.add(Double.parseDouble(sLineStrings[3]));
        arrDNilai.add(Double.parseDouble(sLineStrings[4]));
  
        Mahasiswa mahasiswa = new Mahasiswa(Integer.parseInt(sLineStrings[0]), sLineStrings[1], arrDNilai);
        mahasiswas.add(mahasiswa);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return mahasiswas;
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

  // this method did one job:
  // 1. search the object with iID and sName
  // 2. if it found a duplicate from sName
  // 3. it ask the user to choose between the iID
  // 4. and then pass the iID to the static method updateMahasiswa(iID)
  public static ArrayList<Mahasiswa> editMahasiswaMenu(ArrayList<Mahasiswa> mahasiswas) {
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    int iID = 0;
    String sName = "";
    try {
      System.out.print("Enter the student's ID: ");
      iID = Integer.parseInt(br.readLine());

      System.out.print("Enter the student's Name: ");
      sName = br.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (int i = 0; i < mahasiswas.size(); i++) {
      ArrayList<Mahasiswa> temporary = new ArrayList<>();
      Mahasiswa mahasiswaiID = mahasiswas.get(i);

      // check if iID exists
      if (mahasiswaiID.getiID() == iID) {
        temporary.add(mahasiswaiID);
      }

      // check for duplicate names.
      for (int j = 0; j < mahasiswas.size(); j++) {
        Mahasiswa mahasiswasName = mahasiswas.get(j);
        if (!temporary.contains(mahasiswasName) && mahasiswasName.getsName().equals(sName)) {
          temporary.add(mahasiswasName);
        }
      }

      // if temporary.size() == 0 || > 0 equals to "Mahasiswa found."
      if (temporary.size() == 1) {
        Main.clearConsole();
        System.out.println("Mahasiswa found.");
        mahasiswas = updateMahasiswa(mahasiswas, iID - 1);
        break;
      } else if (temporary.size() > 0) {
        Main.clearConsole();
        System.out.println("Found 2 students with exact name.");
        for (Mahasiswa mahasiswa : temporary) {
          printMahasiswa(mahasiswa);
        }

        System.out.print("Please enter the student's ID you wish to edit / delete: ");
        try {
          iID = Integer.parseInt(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }

        mahasiswas = updateMahasiswa(mahasiswas, iID - 1);
        break;
      }

    }

    if (mahasiswas.size() == 0) {
      System.out.println("ArrayList<Mahasiswa> is empty.");
    }

    Main.pressEnterToContinue();

    return mahasiswas;
  }

  // it ask the user to edit or delete?
  public static ArrayList<Mahasiswa> updateMahasiswa(ArrayList<Mahasiswa> mahasiswas, int iID) {
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    Mahasiswa mahasiswa = mahasiswas.get(iID);
    Main.clearConsole();
    printMahasiswa(mahasiswa);

    System.out.println("Menu: ");
    System.out.println("1. Edit the student's information.");
    System.out.println("2. Delete the student.");
    System.out.print("Please enter the menu's number: ");
    String decision = "";
    try {
      decision = br.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    switch (decision) {
      case "1":
        mahasiswas = editMahasiswa(mahasiswas, mahasiswa, iID);
        break;
      case "2":
        mahasiswas = deleteMahasiswa(mahasiswas, iID);
        break;
      default:
        System.out.println("Canceling..");
    }

    return mahasiswas;
  }

  // this will return modified arraylist
  public static ArrayList<Mahasiswa> editMahasiswa(ArrayList<Mahasiswa> mahasiswas, Mahasiswa mahasiswa, int iID) {
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

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
        mahasiswas.set(iID, temporary);
        break;
      case "n":
      case "no":
        break;
      default:
        System.out.println("Canceling..");
    }

    return mahasiswas;
  }

  // this will remove mahasiswa using ArrayList.remove(index);
  // this could be expensive as this will move the memory heap.
  public static ArrayList<Mahasiswa> deleteMahasiswa(ArrayList<Mahasiswa> mahasiswas, int iID) {
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    System.out.print("Are you sure to delete the student? Yes / No: ");
    String decision = "";
    try {
      decision = br.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    switch (decision.toLowerCase()) {
      case "y":
      case "yes":
        mahasiswas.remove(iID);
        break;
      case "n":
      case "no":
        break;
      default:
        System.out.println("Canceling..");
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
