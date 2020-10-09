import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriterWorker implements Runnable {
  private String file;
  private Staff staff;
  private int currentWorkDays;

  WriterWorker(Staff staff, String file, int currentWorkDays) {
    this.file = file;
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
    FileWriter fw = null;
    try {
      fw = new FileWriter(this.file, true);
    } catch (IOException e) {
      e.printStackTrace();
    }
    BufferedWriter br = new BufferedWriter(fw);

    String line = "";
    line += staff.getID() + ",";
    line += staff.getNama() + ",";
    line += Integer.toString(staff.getAbsensi()) + ",";
    line += Integer.toString(staff.getIzin()) + ",";

    // JmlTanpaKabar
    if (staff.getIzin() <= 1) {
      line += "0,";
    } else {
      line += Integer.toString(staff.getIzin() - 1) + ",";
    }

    System.out.println(staff.getTotalGaji(this.currentWorkDays));
    String totalGaji = String.format("%.0f", staff.getTotalGaji(this.currentWorkDays));
    line += totalGaji;

    try {
      br.write(line);
      br.newLine();
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
