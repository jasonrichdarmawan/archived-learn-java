import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class HitungTotalGajiUtils {
  public static void printHitungTotalGajiStaff(ArrayList<Staff> staffs) throws Exception {
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

        // AbstractWorker
        System.out.println("Gaji Pokok: " + formatter.format(staff.getfGajiPokok()));
        System.out.println("Tunjangan Pulsa: " + formatter.format(staff.getfTunjanganPulsa()));

        // Staff
        // intentionally, so we don't have to call staff.HitungFTunjanganMakan() 2
        // times.
        float totalfTunjanganMakan = staff.HitungfTunjanganMakan();
        System.out.println("Tunjangan Makan: " + formatter.format(totalfTunjanganMakan));

        System.out.println("==============================");

        System.out.println("Total Gaji: "
            + formatter.format(staff.getfGajiPokok() + staff.getfTunjanganPulsa() + totalfTunjanganMakan));
      } else if (index == staffs.size() - 1) {
        System.out.println("ID karyawan tidak ditemukan.");
      }
    }

    System.out.print("Press enter to continue: ");
    br.readLine();
  }

  public static void printHitungTotalGajiManager(ArrayList<Manager> managers) throws Exception {
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    Locale inLocale = new Locale("id", "ID");
    NumberFormat formatter = NumberFormat.getCurrencyInstance(inLocale);

    System.out.print("Enter the staff's ID: ");

    // question: cara await.
    int iIDKaryawan = Integer.parseInt(br.readLine());

    for (int index = 0; index < managers.size(); index++) {
      Manager manager = managers.get(index);
      if (iIDKaryawan == manager.getiIDKaryawan()) {
        MainUtils.printWorker(manager);

        // Worker
        System.out.println("Gaji Pokok: " + formatter.format(manager.getfGajiPokok()));
        System.out.println("Tunjangan Pulsa: " + formatter.format(manager.getfTunjanganPulsa()));

        // Manager
        System.out.println("Tunjangan Transport: " + formatter.format(manager.HitungfTunjanganTransport()));
        System.out.println("Tunjangan Entertaint: " + formatter.format(manager.HitungfTunjanganEntertaint()));

        System.out.println("==============================");

        System.out.println("Total Gaji: " + formatter.format(manager.getfGajiPokok() + manager.getfTunjanganPulsa()
            + manager.HitungfTunjanganTransport() + manager.HitungfTunjanganEntertaint()));
      } else if (index == managers.size() - 1) {
        System.out.println("ID karyawan tidak ditemukan.");
      }
    }

    System.out.print("Press enter to continue: ");
    br.readLine();
  }
}
