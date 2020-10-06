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

          MainUtils.printMenuPendaftaranKaryawan();

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
          break;
        case "2":
          MainUtils.printMenuAbsensi();

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
          break;
        case "3":
          MainUtils.printMenuHitungTunjangan();

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
          break;
        case "4":
          MainUtils.printMenuHitungTotalGaji();
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