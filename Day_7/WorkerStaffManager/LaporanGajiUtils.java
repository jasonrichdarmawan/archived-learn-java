import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class LaporanGajiUtils {
  public static void printLaporanGaji(ArrayList<Staff> staffs, ArrayList<Manager> managers) {
    Locale inLocale = new Locale("id", "ID");
    NumberFormat formatter = NumberFormat.getCurrencyInstance(inLocale);

    System.out.println("Laporan Gaji Seluruh Karyawan:");
    System.out.println("ID  |     Nama     |     Jabatan     |  Total Gaji  ");

    String format = "%-2d  |  %-10s  |  %-13s  |  %-15s %n";

    // StaffManager is an interface.
    ArrayList<StaffManager> combinedArrayList = new ArrayList<>();

    // bubble sort algorithm
    combinedArrayList.addAll(staffs);
    combinedArrayList.addAll(managers);
    for (int i = 0; i < combinedArrayList.size() - 1; i++) {
      for (int j = 0; j < combinedArrayList.size() - 1; j++) {
        if (combinedArrayList.get(j).getiIDKaryawan() > combinedArrayList.get(j + 1).getiIDKaryawan()) {
          StaffManager worker = combinedArrayList.get(j);
          combinedArrayList.set(j, combinedArrayList.get(j + 1));
          combinedArrayList.set(j + 1, worker);
        }
      }
    }

    for (StaffManager worker : combinedArrayList) {
      float fTotalGaji = 0.0F;
      if (worker instanceof Staff) {
        fTotalGaji = worker.getfGajiPokok() + worker.getfTunjanganPulsa() + worker.HitungfTunjanganMakan();
      } else if (worker instanceof Manager) {
        fTotalGaji = worker.getfGajiPokok() + worker.getfTunjanganPulsa() + worker.HitungfTunjanganTransport()
            + worker.HitungfTunjanganEntertaint();
      }
      System.out.format(format, worker.getiIDKaryawan(), worker.getsNama(), worker.getsJabatan(),
          formatter.format(fTotalGaji));
    }
  }
}
