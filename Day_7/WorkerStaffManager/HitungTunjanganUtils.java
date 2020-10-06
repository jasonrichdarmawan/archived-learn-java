import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class HitungTunjanganUtils {
  public static void printHitungTunjanganStaff(ArrayList<Staff> staffs) throws Exception {
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    Locale inLocale = new Locale("id", "ID");
    NumberFormat formatter = NumberFormat.getCurrencyInstance(inLocale);

    System.out.print("Enter the staff's ID: ");

    // question: cara await.
    int iIDKaryawan = Integer.parseInt(br.readLine());

    for (int index = 0; index < staffs.size(); index++) {
      Staff staff = staffs.get(index);
      if (iIDKaryawan == staff.getiIDKaryawan()) {
        MainUtils.printWorker(staff);
        System.out.println("Tunjangan Pulsa: " + formatter.format(staff.getfTunjanganPulsa()));

        float totalfTunjanganMakan = staff.HitungfTunjanganMakan();
        System.out.println("Tunjangan Makan: " + formatter.format(totalfTunjanganMakan));

        System.out.println("==============================");

        System.out.println("Total Tunjangan: " + formatter.format(staff.getfTunjanganPulsa() + totalfTunjanganMakan));
      } else if (index == staffs.size() - 1) {
        System.out.println("ID karyawan tidak ditemukan.");
      }
    }

    System.out.print("Press enter to continue: ");
    br.readLine();
  }

  static public void printMenuHitungTunjanganManager(ArrayList<Manager> managers) throws Exception {
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    Locale inLocale = new Locale("id", "ID");
    NumberFormat formatter = NumberFormat.getCurrencyInstance(inLocale);

    System.out.println("Menu:");
    System.out.println("1. Hitung Tunjangan Transport.");
    System.out.println("2. Hitung Tunjangan Entertain.");
    System.out.println("3. Kembali ke halaman sebelumnya.");

    String menu = "";
    while (menu != "3") {
      System.out.print("Enter the menu's number: ");
      menu = br.readLine();

      switch (menu) {
        case "1":
          System.out.print("Enter the manager's ID: ");
          int iIDKaryawan = Integer.parseInt(br.readLine());

          for (int index = 0; index < managers.size(); index++) {
            Manager manager = managers.get(index);
            if (iIDKaryawan == manager.getiIDKaryawan()) {
              MainUtils.printWorker(manager);
              System.out.println("Tunjangan Transport: " + formatter.format(manager.HitungfTunjanganTransport()));
            } else if (index == managers.size() - 1) {
              System.out.println("ID karyawan tidak ditemukan.");
            }
          }

          System.out.print("Press enter to continue: ");
          br.readLine();

          menu = "3"; // go back to previous page
          break;
        case "2":
          System.out.print("Enter the manager's ID: ");
          iIDKaryawan = Integer.parseInt(br.readLine());

          for (int index = 0; index < managers.size(); index++) {
            Manager manager = managers.get(index);
            if (iIDKaryawan == manager.getiIDKaryawan()) {
              MainUtils.printWorker(manager);

              System.out.print("Enter the unrecorded amount: ");
              int iEntertaint = Integer.parseInt(br.readLine());

              // for future upgrade: saved to database, currently the
              // SDR does not require to save.
              manager.setiEntertaint(iEntertaint);

              System.out.println("Tunjangan Entertaint: " + formatter.format(manager.HitungfTunjanganEntertaint()));

            } else if (index == managers.size() - 1) {
              System.out.println("ID karyawan tidak ditemukan.");
            }
          }

          System.out.print("Press enter to continue: ");
          br.readLine();

          menu = "3"; // go back to previous page.
          break;
        case "3":
          menu = "3";
          break;
        default:
          System.out.println("Invalid menu. Please enter the correct menu's number: ");
      }
    }

    System.out.print("Press enter to continue: ");
    br.readLine();
  }
}
