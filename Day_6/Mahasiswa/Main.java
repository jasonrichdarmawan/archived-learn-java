import java.io.*;
import java.util.ArrayList;

public class Main {
  static final String file = "./database.txt";

  static ArrayList<Mahasiswa> readDatabase() throws Exception {
    FileReader fr = new FileReader(file);
    BufferedReader br = new BufferedReader(fr);

    String sLine;
    ArrayList<Mahasiswa> ArrayListOfStrings = new ArrayList<Mahasiswa>();
    while ((sLine = br.readLine()) != null) {
      String[] sLineStrings = sLine.split(",");
      Mahasiswa mahasiswa = new Mahasiswa(Integer.parseInt(sLineStrings[0]), sLineStrings[1],
          Float.parseFloat(sLineStrings[2]));
      ArrayListOfStrings.add(mahasiswa);
    }

    br.close();
    fr.close();

    return ArrayListOfStrings;
  }

  // reuseable sysout
  static void printMenu() {
    System.out.println("Menu: ");
    System.out.println("1. Daftarkan mahasiswa baru.");
    System.out.println("2. Ubah data mahasiswa.");
    System.out.println("3. Hapus mahasiswa dari daftar.");
    System.out.println("4. Lihat laporan data mahasiswa.");
    System.out.println("5. Simpan laporan ke ./database.txt.");
    System.out.println("0. Exit.");
  }

  static void printMenuSort() {
    System.out.println("Urutkan berdasarkan:");
    System.out.println("1. ID.");
    System.out.println("2. Nama.");
    System.out.println("3. Nilai.");
    System.out.println("4. Kembali ke halaman sebelumnya.");
  }

  static void printDatabase(ArrayList<Mahasiswa> tempMahasiswas) {
    for (int index = 0; index < tempMahasiswas.size(); index++) {
      int tempID = tempMahasiswas.get(index).getID();
      String tempName = tempMahasiswas.get(index).getName();
      float tempScore = tempMahasiswas.get(index).getScore();

      System.out.println(tempID + "   |" + tempName + "|  " + tempScore);
    }
  }

  public static void main(String[] args) throws Exception {

    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    // readDatabase() similar to ArrayList<Mahasiswa> mahasiswa = new
    // ArrayList<Mahasiswa>();
    ArrayList<Mahasiswa> mahasiswas = readDatabase();

    String menu = "";
    while (!menu.equals("0")) {
      printMenu();
      System.out.print("Enter the menu's number: ");
      menu = br.readLine();

      // reuseable attr for switch (menu) {}
      int id;
      String name;
      String score;

      switch (menu) {
        case "1":
          System.out.print("Enter the student's name: ");
          name = br.readLine();

          System.out.print("Enter the student's score: ");
          score = br.readLine();

          Mahasiswa mahasiswa1 = new Mahasiswa(mahasiswas.size() + 1, name, Float.parseFloat(score));
          mahasiswas.add(mahasiswa1);

          break;

        case "2":
          // 1. Search from the ArrayList<Mahasiswas> mahasiswas.
          System.out.print("Enter the student's ID: ");
          id = Integer.parseInt(br.readLine());

          for (int index = 0; index < mahasiswas.size(); index++) {
            if (id == mahasiswas.get(index).getID()) {
              Mahasiswa tempMahasiswa = mahasiswas.get(index);
              System.out.println("Current student's name: " + tempMahasiswa.getName() + " with current's score: "
                  + tempMahasiswa.getScore());
              System.out.print("Enter the student's new name: ");
              name = br.readLine();

              System.out.print("Enter the student's new score: ");
              score = br.readLine();

              // 2. ArrayList method .set() to replace with new data.
              mahasiswas.set(index, new Mahasiswa(tempMahasiswa.getID(), name, Float.parseFloat(score)));

              // 3. break to avoid expensive query.
              break;
            }
          }

          break;
        case "3":
          // similar to case "2":
          // the ID is intentionally not re-sorted because in real life
          // student ID rarely changes.
          System.out.print("Enter the student's ID: ");
          id = Integer.parseInt(br.readLine());

          for (int index = 0; index < mahasiswas.size(); index++) {
            if (id == mahasiswas.get(index).getID()) {
              Mahasiswa tempMahasiswa = mahasiswas.get(index);
              System.out.println("Current student's name: " + tempMahasiswa.getName() + " with current's score: "
                  + tempMahasiswa.getScore());

              System.out.println("Proceed to delete? Y / N");
              menu = br.readLine();
              switch (menu.toLowerCase()) {
                case "y":
                case "yes":
                  mahasiswas.remove(index);
                  break;
                case "n":
                case "no":
                  break;
                default:
                  System.out.println("Invalid menu. Canceling..");
              }

              break;
            }
          }

          break;
        case "4":
          printMenuSort();

          // reuseable ArrayList<Mahasiswa> tempMahasiswas for case "1" & case "2"
          // the intention is to prevent unnecessary edit to the original ArrayList.
          // which is used by case "5" to BufferedWrite method .write() to the file.
          ArrayList<Mahasiswa> tempMahasiswas = mahasiswas;

          System.out.print("Enter the menu's number: ");
          menu = br.readLine();
          System.out.println("ID  |          Nama          |  Nilai");
          switch (menu) {
            case "1":
              for (int index = 0; index < mahasiswas.size(); index++) {
                int tempID = mahasiswas.get(index).getID();
                String tempName = mahasiswas.get(index).getName();
                float tempScore = mahasiswas.get(index).getScore();

                System.out.println(tempID + "   |" + tempName + "|  " + tempScore);
              }

              break;
            case "2":

              // 1. ArrayList<Object> -> just get the name into an ArrayList.
              ArrayList<String> tempNames = new ArrayList<String>();
              for (int index = 0; index < mahasiswas.size(); index++) {
                tempNames.add(mahasiswas.get(index).getName());
              }

              // 2. bubble sort algorithm (n^2). this could be expensive.
              for (int i = 0; i < tempNames.size() - 1; i++) {
                for (int j = 0; j < tempNames.size() - 1; j++) {
                  if (tempNames.get(j).compareTo(tempNames.get(j + 1)) > 0) {
                    String tempName = tempNames.get(j);
                    tempNames.set(j, tempNames.get(j + 1));
                    tempNames.set(j + 1, tempName);

                    Mahasiswa tempMahasiswa = tempMahasiswas.get(j);
                    tempMahasiswas.set(j, tempMahasiswas.get(j + 1));
                    tempMahasiswas.set(j + 1, tempMahasiswa);
                  }
                }
              }

              printDatabase(tempMahasiswas);

              break;
            case "3":
              // similar to case "2"
              ArrayList<Float> tempScores = new ArrayList<Float>();

              for (int index = 0; index < mahasiswas.size(); index++) {
                tempScores.add(mahasiswas.get(index).getScore());
              }

              for (int i = 0; i < tempScores.size() - 1; i++) {
                for (int j = 0; j < tempScores.size() - 1; j++) {
                  // [3, 1, 4] => [1, 3, 4]
                  if (tempScores.get(j) < tempScores.get(j + 1)) {
                    float tempScore = tempScores.get(j);
                    tempScores.set(j, tempScores.get(j + 1));
                    tempScores.set(j + 1, tempScore);

                    Mahasiswa tempMahasiswa = tempMahasiswas.get(j);
                    tempMahasiswas.set(j, tempMahasiswas.get(j + 1));
                    tempMahasiswas.set(j + 1, tempMahasiswa);
                  }
                }
              }

              printDatabase(tempMahasiswas);

              break;
            case "4":
              break;
            default:
              System.out.print("Invalid menu. Please enter correct menu's number: ");
              menu = br.readLine();
          }
          break;
        case "5":
          // BufferedWriter is used so we don't have to write to the file
          // char by char, and we don't have to use BufferedOutputStream .flush();
          // which is neat.
          FileWriter writer = new FileWriter(file);
          BufferedWriter buffer = new BufferedWriter(writer);

          for (int index = 0; index < mahasiswas.size(); index++) {
            Mahasiswa tempMahasiswa = mahasiswas.get(index);
            String temp = "";
            temp += tempMahasiswa.getID() + "," + tempMahasiswa.getName() + "," + tempMahasiswa.getScore();
            buffer.write(temp);

            if (index < mahasiswas.size() - 1) {
              buffer.newLine();
            }
          }

          buffer.close();
          System.out.println("Success.");

          break;
        case "0":
          break;
        default:
          System.out.print("Invalid menu. Please enter correct menu's number: ");
          menu = br.readLine();
      }
    }
  }
}

class Mahasiswa {
  // encapsulation method
  private int id;
  private String sName;
  private float fScore;

  Mahasiswa(int id, String sName, float fScore) {
    this.id = id;
    this.sName = sName;
    this.fScore = fScore;
  }

  int getID() {
    return id;
  }

  String getName() {
    return sName;
  }

  float getScore() {
    return fScore;
  }
}