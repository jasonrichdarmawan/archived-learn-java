import java.io.*;
import java.util.ArrayList;

public class Staff extends AbstractWorker {
  private String sJabatan;

  static final String file = "./database.txt";

  // alternative 1: with constructor();
  // Staff(int iIDKaryawan, String sNama, String sJabatan, int iAbsensi) {
  // // alternative a:
  // // super(iIDKaryawan, sNama, iAbsensi); // create reference to the Object
  // constructed with Class Worker.

  // // alternative b: super() alternative
  // // but if you notice, this.iIDKaryawan is not Staff's attribute.
  // // this will create confusion.
  // this.iIDKaryawan = iIDKaryawan;
  // this.sNama = sNama;
  // this.iAbsensi = iAbsensi;

  // this.sJabatan = sJabatan;
  // }

  public String getsJabatan() {
    return sJabatan;
  }

  public void setsJabatan(String sJabatan) {
    this.sJabatan = sJabatan;
  }

  static public ArrayList<Staff> readDatabase() throws Exception {
    FileReader fr = new FileReader(file);
    BufferedReader br = new BufferedReader(fr);

    String sLine;
    ArrayList<Staff> ArrayListOfStaff = new ArrayList<Staff>();
    while ((sLine = br.readLine()) != null) {
      String[] sLineStrings = sLine.split(",");

      // alternative 1: with constructor
      // Staff staff = new Staff(Integer.parseInt(sLineStrings[0]), sLineStrings[1],
      // sLineStrings[2],
      // Integer.parseInt(sLineStrings[3]));

      // alternative 2: with getters and settrs method.
      Staff newStaff = new Staff();
      newStaff.setiIDKaryawan(Integer.parseInt(sLineStrings[0]));
      newStaff.setsNama(sLineStrings[1]);
      newStaff.setsJabatan(sLineStrings[2]);
      newStaff.setiAbsensi(Integer.parseInt(sLineStrings[3]));

      ArrayListOfStaff.add(newStaff);
    }

    br.close();
    fr.close();

    return ArrayListOfStaff;
  }

  static public void printMenu() {
    System.out.println("Menu: ");
    System.out.println("1. Daftarkan staff baru.");
    System.out.println("2. Daftarkan absensi staff.");
    System.out.println("3. Tampilkan laporan absensi seluruh staff.");
    System.out.println("4. Keluar dari aplikasi ini.");
  }

  static public void printStaff(Staff staff) {
    System.out.println("ID  |     Nama     |     Jabatan     |  Absensi/hari");
    String format = "%-2d  |  %-10s  |  %-13s  |  %-12d %n";
    System.out.format(format, staff.getiIDKaryawan(), staff.getsNama(), staff.getsJabatan(), staff.getiAbsensi());
  }

  public static void main(String[] args) throws Exception {
    ArrayList<Staff> staffs = readDatabase();
    printMenu();

    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    String menu = "";
    while (menu != "4") {
      System.out.print("Enter the menu's number: ");
      menu = br.readLine();

      switch (menu) {
        case "1":
          System.out.print("Enter the staff's name: ");
          String sNama = br.readLine();

          System.out.print("Enter the staff's role: ");
          String sJabatan = br.readLine();

          // staffs.add(new Staff(staffs.size() + 1, sNama, sJabatan, 0));

          Staff newStaff = new Staff();
          newStaff.setiIDKaryawan(staffs.size() + 1);
          newStaff.setsNama(sNama);
          newStaff.setsJabatan(sJabatan);
          newStaff.setiAbsensi(0);
          staffs.add(newStaff);

          break;
        case "2":
          System.out.print("Enter the staff's ID: ");

          // question: cara await.
          int iIDKaryawan = Integer.parseInt(br.readLine());

          for (int index = 0; index < staffs.size(); index++) {
            Staff staff = staffs.get(index);
            if (iIDKaryawan == staff.getiIDKaryawan()) {
              printStaff(staff);

              // opsi 1: tambahin bisa lebih dari 1.
              // System.out.print("Masukkan jumlah absensi: ");
              // int iAbsensi = Integer.parseInt(br.readLine());
              // staffs.set(index, new Staff(staff.getiIDKaryawan(), staff.getsNama(),
              // staff.getsJabatan(), iAbsensi));

              // opsi 2: tambah absensi 1 per 1
              System.out.print("Daftarkan absensi? Yes / No: ");
              String decision = br.readLine();
              switch (decision.toLowerCase()) {
                case "y":
                case "yes":
                  staff.tambahAbsensi();
                  break;
                case "n":
                case "no":
                  break;
              }

              break;
            }

            if (index == staffs.size() - 1) {
              System.out.println("ID karyawan tidak ditemukan.");
            }
          }

          break;
        case "3":
          System.out.println("Laporan Absensi:");
          System.out.println("ID  |     Nama     |     Jabatan     |  Absensi/hari");

          // alternative 1: require super() constructor to work.
          // Collections.sort(staffs, Comparator.comparingInt(Staff::getiIDKaryawan));

          // alternative 2:
          // bubble sort algorithm (n^2). this could be expensive.
          for (int i = 0; i < staffs.size() - 1; i++) {
            for (int j = 0; j < staffs.size() - 1; j++) {
              if (staffs.get(j).getiIDKaryawan() > staffs.get(j + 1).getiIDKaryawan()) {
                Staff staff = staffs.get(j);
                staffs.set(j, staffs.get(j + 1));
                staffs.set(j + 1, staff);
              }
            }
          }

          String format = "%-2d  |  %-10s  |  %-13s  |  %-12d %n";
          for (Staff staff : staffs) {
            System.out.format(format, staff.getiIDKaryawan(), staff.getsNama(), staff.getsJabatan(),
                staff.getiAbsensi());
          }

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