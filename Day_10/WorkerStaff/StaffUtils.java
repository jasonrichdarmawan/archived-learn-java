import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.io.BufferedReader;

public class StaffUtils {

  public static void printStaffsSalary(ArrayList<Staff> staffs) {
    Locale inLocale = new Locale("id", "ID");
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(inLocale);

    String format = "%-10s  |  %-9s  %n";

    System.out.println("Nama        |  Total Gaji");
    for (Staff staff : staffs) {
      System.out.format(format, staff.getNama(), currencyFormatter.format(staff.getTotalGaji()));
    }

  }

  // not defined in SDR.
  public static void printStaffInfo(int id, String nama, float gajipokok, int absensi, int izin) {
    Locale inLocale = new Locale("id", "ID");
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(inLocale);

    int permittedLeave = 1; // for future development. not defined in SDR.
    System.out.println("ID: " + id);
    System.out.println("Nama: " + nama);
    System.out.println("Gaji Pokok: " + currencyFormatter.format(gajipokok));
    System.out.println("Absensi: " + absensi);
    System.out.println("Izin: " + izin);
    int unpermittedLeave = 0;
    if (izin > permittedLeave) {
      unpermittedLeave = izin - permittedLeave;
    }
    System.out.println("Tanpa Kabar: " + unpermittedLeave);
  }

  public static void printStaffInfo(Staff staff) {
    int id = staff.getID();
    String nama = staff.getNama();
    float gajipokok = staff.getGajiPokok();
    int absensi = staff.getAbsensi();
    int izin = staff.getIzin();

    printStaffInfo(id, nama, gajipokok, absensi, izin);
  }

  public static ArrayList<Staff> getStaffs(String path) {
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader(path));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    String staff = "";
    ArrayList<Staff> staffs = new ArrayList<>();

    try {
      br.readLine(); // to skip first line.
      while ((staff = br.readLine()) != null) {
        String[] arrStaff = staff.split(",");

        int id = Integer.parseInt(arrStaff[0]);
        String name = arrStaff[1];
        float gajipokok = Float.parseFloat(arrStaff[2]);
        int absensi = Integer.parseInt(arrStaff[3]);
        int izin = Integer.parseInt(arrStaff[4]);

        Staff newStaff = new Staff(id, name, gajipokok, absensi, izin);
        staffs.add(newStaff);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return staffs;
  }

  public static boolean complete(ArrayList<Staff> staffs) {
    int currentWorkDays = 22; // for future development.

    // if absensi + izin != currentWorkdays then return false.
    for (Staff staff : staffs) {
      if (staff.getAbsensi() + staff.getIzin() != currentWorkDays) {
        return false;
      }
    }

    return true;
  }
}
