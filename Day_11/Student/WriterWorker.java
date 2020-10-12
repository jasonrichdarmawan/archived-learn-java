import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriterWorker implements Runnable {
  private String file;
  private MahasiswaBlueprint mahasiswa;
  private int currentWorkDays;

  WriterWorker(MahasiswaBlueprint mahasiswa, String file) {
    this.file = file;
    this.mahasiswa = mahasiswa;
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
    line += mahasiswa.getID() + ",";
    line += mahasiswa.getNama() + ",";
    String nilaiMedian = String.format("%.02f", mahasiswa.HitungNilaiMedian());
    line += nilaiMedian;

    try {
      br.write(line);
      br.newLine();
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
