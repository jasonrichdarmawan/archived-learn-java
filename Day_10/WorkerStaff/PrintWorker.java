import java.io.BufferedReader;
import java.io.FileReader;
import java.text.NumberFormat;
import java.util.Locale;

public class PrintWorker implements Runnable {
  private Staff staff;
  private int currentWorkDays;

  PrintWorker(Staff staff, int currentWorkDays) {
    this.staff = staff;
    this.currentWorkDays = currentWorkDays;
    Thread thread = new Thread(this);
    thread.start();
    try {
      // this is guarantee "Threads are executed in order."
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void run() {
    Locale inLocale = new Locale("id", "ID");
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(inLocale);

    System.out.println("ID: " + staff.getID());
    System.out.println("Nama: " + staff.getNama());
    System.out.println("Absensi: " + Integer.toString(staff.getAbsensi()));
    System.err.println("Izin: " + Integer.toString(staff.getIzin()));
    if (staff.getIzin() <= 1) {
      System.out.println("Tanpa Kabar: 0");
    } else {
      System.out.println("Tanpa Kabar: " + Integer.toString(staff.getIzin() - 1));
    }
    System.out.println("Total Gaji: " + currencyFormatter.format(staff.getTotalGaji(currentWorkDays)));
    
    // StaffUtils.printStaffInfo(this.staff); // deprecated.

    System.out.println("");
  }
}
