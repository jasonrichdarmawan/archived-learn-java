import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class MenuUtils {
  public static void printMainMenu() {
    Main.clearConsole();
    System.out.println("MENU");
    System.out.println("1. Input Data Karyawan.");
    System.out.println("2. Edit Data Karyawan.");
    System.out.println("3. Masukkan Absensi Karyawan."); // in SDR == 3. Absensi Karyawan.
    System.out.println("4. Masukkan Izin Karyawan."); // in SDR == 4. Izin Karyawan.
    System.out.println("5. Hitung Total Gaji Seluruh Karyawan."); // in SDR == 5. Hitung Total Gaji Karyawan.
    System.out.println("6. Tampilkan Laporan per Karyawan");
    // in SDR == 7. Tampilkan Laporan Seluruh Karyawan + Tulis Data di
    // LaporanKaryawan.txt (Multithreading)
    System.out.println("7. Simpan Laporan Seluruh Karyawan ke LaporanKaryawan.txt");
    System.out.println("99. Exit");
    System.out.print("Enter the menu's number: ");
  }

  public static void showMainMenu(BufferedReader br, ArrayList<Staff> staffs, int currentWorkDays) {
    boolean isExit = false;
    while (isExit == false) {
      String menu = "";
      Main.pressEnterToContinue(br); // to prevent Main.clearConsole(); called right away.
      printMainMenu();
      try {
        menu = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      switch (menu) {
        case "1":
          staffs = InputDataKaryawan.showMenu(br, staffs, currentWorkDays);
          break;
        case "2":
          staffs = EditDataKaryawan.showMenu(br, staffs, currentWorkDays);
          break;
        case "3":
          staffs = MasukkanAbsensiKaryawan.showMenu(br, staffs);
          break;
        case "4":
          staffs = MasukkanIzinKaryawan.showMenu(br, staffs);
          break;
        case "5":
          if (StaffUtils.complete(staffs, currentWorkDays)) {
            Main.clearConsole();
            StaffUtils.printStaffsSalary(staffs, currentWorkDays);
          } else {
            System.out.println("Please complete the attedance and leaves for each staff.");
          }
          break;
        case "6":
          SearchStaffUtils search = new SearchStaffUtils(staffs, br);
          StaffUtils.printStaffReport(search.getFocusStaff(), currentWorkDays);
          break;
        case "7":
          if (StaffUtils.complete(staffs, currentWorkDays)) {
            Main.clearConsole();
            NormalThread.print(3, staffs, currentWorkDays);
            NormalThread.write(3, staffs, currentWorkDays);
          } else {
            System.out.println("Please complete the attedance and leaves for each staff.");
          }
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
