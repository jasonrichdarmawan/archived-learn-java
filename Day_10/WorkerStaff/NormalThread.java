import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class NormalThread {
  public static void print(int workersSize, ArrayList<Staff> staffs, int currentWorkDays) {
    int i = 0;
    for (int j = 0; j <= workersSize; j++) {
      for (; i < staffs.size(); i++) {
        Staff staff = staffs.get(i);
        // max worker == workersSize
        while (Thread.activeCount() == workersSize) {
        }
        PrintWorker worker = new PrintWorker(staff, currentWorkDays);
      }
    }

  }

  public static void write(int workersSize, ArrayList<Staff> staffs, int currentWorkDays) {
    String file = "./LaporanKaryawan.txt";
    String firstline = "ID,Nama,JmlAbsensi,JmlCutiTerpakai,JmlTanpaKabar,TotalGaji";
    cleanup(file, firstline);
    int i = 0;
    for (int j = 0; j <= workersSize; j++) {
      // max worker == workersSize
      for (; i < staffs.size(); i++) {
        Staff staff = staffs.get(i);
        while (Thread.activeCount() == workersSize + 1) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        WriterWorker worker = new WriterWorker(staff, file, currentWorkDays);
      }
    }
  }

  // if does not exist => create new file
  // if exist => emptied it.
  public static void cleanup(String file, String firstline) {
    FileWriter fw = null;
    try {
      fw = new FileWriter(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
    BufferedWriter br = new BufferedWriter(fw);

    try {
      br.write(firstline);
      br.newLine();
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
