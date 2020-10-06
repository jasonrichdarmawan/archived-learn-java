import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) throws Exception {
    ArrayList<Staff> staffs = MainUtils.createArrayListOfObjectStaffs();
    ArrayList<Manager> managers = MainUtils.createArrayListOfObjectManagers();

    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    String menu = "";
    while (menu != "6") {
      MainUtils.printMenu();
      System.out.print("Enter the menu's number: ");
      menu = br.readLine();

      switch (menu) {
        case "1":
          MainUtils.printMenuPendaftaranKaryawan(staffs, managers);
          break;
        case "2":
          MainUtils.printMenuAbsensi(staffs, managers);
          break;
        case "3":
          MainUtils.printMenuHitungTunjangan(staffs, managers);
          break;
        case "4":
          MainUtils.printMenuHitungTotalGaji(staffs, managers);
          break;
        case "5":
          LaporanGajiUtils.printLaporanGaji(staffs, managers);
          break;
        case "6":
          menu = "6";
          break;
        default:
          System.out.println("Invalid menu's number. Please enter the correct menu's number.");
      }
    }
  }
}