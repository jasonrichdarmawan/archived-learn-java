import java.io.BufferedReader;
import java.io.FileReader;

public class PrintWorker implements Runnable {
  private MahasiswaBlueprint mahasiswa;

  PrintWorker(MahasiswaBlueprint mahasiswa) {
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
    MahasiswaUtils.printMahasiswaInfo(this.mahasiswa);
  }
}
