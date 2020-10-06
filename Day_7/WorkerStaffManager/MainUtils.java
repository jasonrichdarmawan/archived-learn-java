import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// MainUtils is all of static method of Main.
public class MainUtils {
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

  static public ArrayList<Staff> createArrayListOfObjectStaffs() throws Exception {
    FileReader fr = new FileReader(file);
    BufferedReader br = new BufferedReader(fr);

    String sLine;

    ArrayList<Staff> arrayListOfObjectStaffs = new ArrayList<Staff>();

    while ((sLine = br.readLine()) != null) {
      String[] sLineStrings = sLine.split(",");

      // step 1. logic to prevent creating new object if sJabatan is not match.
      if (!sLineStrings[2].equals("Staff"))
        continue;

      // step 2. logic to create new object.

      // alternative 1: with constructor
      // Staff staff = new Staff(Integer.parseInt(sLineStrings[0]), sLineStrings[1],
      // sLineStrings[2],
      // Integer.parseInt(sLineStrings[3]));

      // alternative 2: with getters and settrs method.
      Staff newStaff = new Staff();
      newStaff.setiIDKaryawan(Integer.parseInt(sLineStrings[0]));
      newStaff.setsNama(sLineStrings[1]);
      newStaff.setsJabatan(sLineStrings[2]);
      newStaff.setfTunjanganPulsa(Float.parseFloat(sLineStrings[3]));
      newStaff.setfGajiPokok(Float.parseFloat(sLineStrings[4]));
      newStaff.setiAbsensi(Integer.parseInt(sLineStrings[5]));

      arrayListOfObjectStaffs.add(newStaff);

    }

    br.close();
    fr.close();

    return arrayListOfObjectStaffs;
  }

  static public ArrayList<Manager> createArrayListOfObjectManagers() throws Exception {
    FileReader fr = new FileReader(file);
    BufferedReader br = new BufferedReader(fr);

    String sLine;

    ArrayList<Manager> arrayListOfObjectManagers = new ArrayList<Manager>();

    while ((sLine = br.readLine()) != null) {
      String[] sLineStrings = sLine.split(",");

      // step 1. logic to prevent creating new object if sJabatan is not match.
      if (!sLineStrings[2].equals("Manager"))
        continue;

      // step 2. logic to create new object.

      // alternative is similar to createArrayListOfObjectStaffs()
      Manager newManager = new Manager();
      newManager.setiIDKaryawan(Integer.parseInt(sLineStrings[0]));
      newManager.setsNama(sLineStrings[1]);
      newManager.setsJabatan(sLineStrings[2]);
      newManager.setfTunjanganPulsa(Float.parseFloat(sLineStrings[3]));
      newManager.setfGajiPokok(Float.parseFloat(sLineStrings[4]));
      newManager.setiAbsensi(Integer.parseInt(sLineStrings[5]));

      // iEntertaint is a persistent attribute and unique to Object with the Manager
      // class.
      newManager.setiEntertaint(Integer.parseInt(sLineStrings[6]));

      arrayListOfObjectManagers.add(newManager);

    }

    br.close();
    fr.close();

    return arrayListOfObjectManagers;
  }

  static public void printMenu() {
    System.out.println("Menu:");
    System.out.println("1. Daftarkan Karyawan Baru.");
    // -> 1. Daftarkan Staff baru.
    // -> 2. Daftarkan Manager baru.

    System.out.println("2. Daftarkan Absensi Karyawan.");
    // -> 1. Daftarkan Absensi Staff.
    // -> 2. Daftarkan Absensi Manager.

    System.out.println("3. Hitung Tunjangan Karyawan");
    // -> 1. Hitung Tunjangan Staff -> Input ID
    // -> 2. Hitung Tunjangan Manager
    // // -> 1. Hitung Tunjangan Transport -> Input ID
    // // -> 2. Hitung Tunjangan Entertaint -> Input ID -> Input Lama Entertain

    System.out.println("4. Hitung Total Gaji (Gaji Pokok + Tunjangan)");
    // -> 1. Gaji Staff -> Input ID // di luar requirement tugas
    // -> 2. Gaji Manager -> Input ID // sama.

    System.out.println("5. Tampilkan Laporan Gaji Seluruh Karyawan."); // berubah dari case 3: menjadi case 5:
    System.out.println("6. Keluar dari aplikasi ini."); // berubah dari case 4: menjadi case 6:
  }

  static public void printWorker(Manager worker) {
    System.out.println("ID  |     Nama     |     Jabatan     |  Absensi/hari");
    String format = "%-2d  |  %-10s  |  %-13s  |  %-12d %n";
    System.out.format(format, worker.getiIDKaryawan(), worker.getsNama(), worker.getsJabatan(), worker.getiAbsensi());
  }

  static public void printWorker(Staff worker) {
    System.out.println("ID  |     Nama     |     Jabatan     |  Absensi/hari");
    String format = "%-2d  |  %-10s  |  %-13s  |  %-12d %n";
    System.out.format(format, worker.getiIDKaryawan(), worker.getsNama(), worker.getsJabatan(), worker.getiAbsensi());
  }

  static public void printMenuPendaftaranKaryawan(ArrayList<Staff> staffs, ArrayList<Manager> managers)
      throws Exception {
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    System.out.println("Menu:");
    System.out.println("1. Daftarkan Staff Baru.");
    System.out.println("2. Daftarkan Manager Baru.");
    System.out.println("3. Kembali ke menu sebelumnya");

    String menu = "";
    while (menu != "3") {
      System.out.print("Enter the menu's number: ");
      menu = br.readLine();
      switch (menu) {
        case "1":
          DaftarKaryawanUtils dku = new DaftarKaryawanUtils();
          dku.setWorkerAttribute(); //

          // alternative 1: with constructor
          // staffs.add(new Staff(staffs.size() + 1, sNama, sJabatan, 0));

          // alternative 2: getters and settrs.
          Staff newStaff = new Staff();
          newStaff.setiIDKaryawan(staffs.size() + managers.size() + 1);
          newStaff.setsNama(dku.getsNama());
          newStaff.setfTunjanganPulsa(dku.getfTunjanganPulsa());
          newStaff.setfGajiPokok(dku.getfGajiPokok());
          newStaff.setsJabatan("Staff");
          staffs.add(newStaff);

          // get back to the previous page.
          menu = "3";
          break;
        case "2":
          // change with new reference to object.
          dku = new DaftarKaryawanUtils();
          dku.setWorkerAttribute();

          // alternative is similar to case "1"
          Manager newManager = new Manager();
          newManager.setiIDKaryawan(staffs.size() + managers.size() + 1);
          newManager.setsNama(dku.getsNama());
          newManager.setfTunjanganPulsa(dku.getfTunjanganPulsa());
          newManager.setfGajiPokok(dku.getfGajiPokok());
          newManager.setsJabatan("Manager");
          managers.add(newManager);

          // get back to the previous page.
          menu = "3";
          break;
        case "3":
          menu = "3";
          break;
        default:
          System.out.println("Invalid menu. Please enter the correct menu's number: ");
      }
    }
  }

  static public void printMenuAbsensi(ArrayList<Staff> staffs, ArrayList<Manager> managers) throws Exception {
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    System.out.println("Menu:");
    System.out.println("1. Daftarkan Absensi Staff.");
    System.out.println("2. Daftarkan Absensi Manager.");
    System.out.println("3. Kembali ke menu sebelumnya");

    String menu = "";
    while (menu != "3") {
      System.out.print("Enter the menu's number: ");
      menu = br.readLine();
      switch (menu) {
        case "1":
          // question: overloading method, both methods have same erasure.
          AbsensiUtils.printMenuStaff(staffs);

          // get back to the previous page.
          menu = "3";
          break;
        case "2":
          AbsensiUtils.printMenuManager(managers);

          // get back to the previous page.
          menu = "3";
          break;
        case "3":
          menu = "3";
          break;
        default:
          System.out.println("Invalid menu. Please enter the correct menu's number: ");
      }
    }
  }

  static public void printMenuHitungTunjangan(ArrayList<Staff> staffs, ArrayList<Manager> managers) throws Exception {
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    System.out.println("Menu:");
    System.out.println("1. Hitung Tunjangan Staff.");
    System.out.println("2. Hitung Tunjangan Manager.");
    System.out.println("3. Kembali ke halaman sebelumnya.");

    String menu = "";
    while (menu != "3") {
      System.out.print("Enter the menu's number: ");
      menu = br.readLine();
      switch (menu) {
        case "1":
          HitungTunjanganUtils.printHitungTunjanganStaff(staffs);

          // get back to the previous page.
          menu = "3";
          break;
        case "2":
          HitungTunjanganUtils.printMenuHitungTunjanganManager(managers);

          // get back to the previous page.
          menu = "3";
          break;
        case "3":
          menu = "3";
          break;
        default:
          System.out.println("Invalid menu. Please enter the correct menu's number: ");
      }
    }
  }

  static public void printMenuHitungTotalGaji(ArrayList<Staff> staffs, ArrayList<Manager> managers) throws Exception{
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    System.out.println("Menu:");
    System.out.println("1. Hitung Total Gaji Staff.");
    System.out.println("2. Hitung Total Gaji Manager.");
    System.out.println("3. Kembali ke halaman sebelumnya.");

    String menu = "";
    while (menu != "3") {
      System.out.print("Enter the menu's number: ");
      menu = br.readLine();
      switch (menu) {
        case "1":
          HitungTotalGajiUtils.printHitungTotalGajiStaff(staffs);

          // get back to the previous page.
          menu = "3";
          break;
        case "2":
          HitungTotalGajiUtils.printHitungTotalGajiManager(managers);

          // get back to the previous page.
          menu = "3";
          break;
        case "3":
          menu = "3";
          break;
        default:
          System.out.println("Invalid menu. Please enter the correct menu's number: ");
      }
    }
  }
}